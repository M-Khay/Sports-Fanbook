package com.yourself.sportsfanbook.ui

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yourself.searchyourcityweather.utils.NetworkConnectivity
import com.yourself.sportsfanbook.R
import com.yourself.sportsfanbook.data.ApiResult
import com.yourself.sportsfanbook.data.Error
import com.yourself.sportsfanbook.data.Loading
import com.yourself.sportsfanbook.data.Success
import com.yourself.sportsfanbook.data.team.Team
import com.yourself.sportsfanbook.di.ComponentInjector
import com.yourself.sportsfanbook.ui.team.ActionBarCallBack
import com.yourself.sportsfanbook.ui.team.rv.FavouriteItemClickListener
import com.yourself.sportsfanbook.ui.team.rv.TeamListAdapter
import com.yourself.sportsfanbook.utils.AppAnalytics
import kotlinx.android.synthetic.main.team_list_fragment.*


class TeamListFragment : Fragment() {
    private lateinit var viewModel: TeamListViewModel
    private lateinit var adapter: TeamListAdapter

    private lateinit var actionBarListener: ActionBarCallBack

    companion object {
        val TAG = TeamListFragment::class.java.name
        fun newInstance() = TeamListFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ActionBarCallBack) {
            actionBarListener = context
        } else {
            throw ClassCastException("$context must implement ActionBarCallBack")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.team_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(TeamListViewModel::class.java).also {
            ComponentInjector.component.inject(it)
        }

        viewModel.teamListState.observe(this.viewLifecycleOwner, teamListObserver)
        search_go.setOnClickListener {
            searchTeam()
        }

        search_text.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                v.clearFocus()
                searchTeam()
            }
            true
        }
        adapter = TeamListAdapter(viewModel,activity as FavouriteItemClickListener)
        rv_dictionary_list.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = this@TeamListFragment.adapter
        }

        rv_dictionary_list.addItemDecoration(
            DividerItemDecoration(
                rv_dictionary_list.context,
                DividerItemDecoration.VERTICAL
            )
        )
    }


    override fun onResume() {
        super.onResume()
        actionBarListener.showHideActionBarWith(resources.getString(R.string.app_name), false)

    }

    private fun searchTeam() {
        hideKeyboard()
        val searchText = search_text.text.toString()
        if (TextUtils.isEmpty(searchText)) {
            input_layout.error = resources.getString(R.string.empty_team_name)
            Toast.makeText(activity, "Search Term Cannot be empty", Toast.LENGTH_LONG).show()
        } else {
            input_layout.error = ""
            if (NetworkConnectivity.isNetworkConnected) {
                viewModel.getTeamListFor(searchText)
                Log.d(TAG, "Searched Team : $searchText")
            } else {
                showAlertDialog(
                    resources.getString(R.string.network_error_title),
                    resources.getString(R.string.network_error_message),
                    resources.getString(R.string.alert_dialog_ok)
                )
            }
            AppAnalytics.searchEvent(searchText)
        }
    }


    private val teamListObserver = Observer<ApiResult<List<Team>>> { state ->
        when (state) {
            is Success<List<Team>> -> {
                loading_content.visibility = View.GONE
                rv_dictionary_list.visibility = View.VISIBLE
                adapter.updateTeamList(state.data)
            }
            is Loading -> {
                rv_dictionary_list.visibility = View.GONE
                loading_content.visibility = View.VISIBLE
            }
            is Error -> {
                loading_content.visibility = View.GONE
                input_layout.error = resources.getString(R.string.invalid_team_name)
            }
        }
    }

    private fun showAlertDialog(title: String, message: String, positiveButtonText: String?) {
        MaterialAlertDialogBuilder(activity)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveButtonText) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun hideKeyboard() {
        val keyboard =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        keyboard.hideSoftInputFromWindow(view?.windowToken, 0)
    }


}

