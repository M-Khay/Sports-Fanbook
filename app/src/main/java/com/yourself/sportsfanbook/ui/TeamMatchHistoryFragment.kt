package com.yourself.sportsfanbook.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yourself.sportsfanbook.R
import com.yourself.sportsfanbook.data.ApiResult
import com.yourself.sportsfanbook.data.Error
import com.yourself.sportsfanbook.data.Loading
import com.yourself.sportsfanbook.data.Success
import com.yourself.sportsfanbook.data.game.GameHistory
import com.yourself.sportsfanbook.ui.team.ActionBarCallBack
import com.yourself.sportsfanbook.ui.team.rv.GameHistoryListAdapter
import kotlinx.android.synthetic.main.team_list_fragment.loading_content
import kotlinx.android.synthetic.main.team_match_history_fragment.*

class TeamMatchHistoryFragment : Fragment() {
    private lateinit var viewModel: TeamListViewModel
    private lateinit var adapter: GameHistoryListAdapter


    private lateinit var actionBarListener: ActionBarCallBack

    companion object {
        fun newInstance() = TeamMatchHistoryFragment()
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
        return inflater.inflate(R.layout.team_match_history_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(TeamListViewModel::class.java)
        viewModel.getSelectedTeamGameHistory()

        viewModel.teamGameHistoryListState.observe(this.viewLifecycleOwner, gameHistoryListObserver)
        adapter = GameHistoryListAdapter(viewModel)
        rv_match_history_list.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = this@TeamMatchHistoryFragment.adapter
        }

        rv_match_history_list.addItemDecoration(
            DividerItemDecoration(
                rv_match_history_list.context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun onResume() {
        super.onResume()
        actionBarListener.showHideActionBarWith(viewModel.getSelectedTeamName(), true)

    }

    private val gameHistoryListObserver = Observer<ApiResult<List<GameHistory>>> { state ->
        when (state) {
            is Success<List<GameHistory>> -> {
                loading_content.visibility = View.GONE
                adapter.updateGameHistoryList(state.data)
            }
            is Loading -> {
                loading_content.visibility = View.VISIBLE
            }
            is Error -> {
                loading_content.visibility = View.GONE
                showAlertDialog(
                    resources.getString(R.string.match_history_error),
                    resources.getString(R.string.no_match_history_found),
                    resources.getString(R.string.alert_dialog_go_back)
                )
            }
        }
    }

    private fun showAlertDialog(title: String, message: String, positiveButtonText: String?) {
        MaterialAlertDialogBuilder(activity)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveButtonText) { dialog, _ ->
                dialog.dismiss()
                activity?.onBackPressed()
            }
            .show()
    }
}
