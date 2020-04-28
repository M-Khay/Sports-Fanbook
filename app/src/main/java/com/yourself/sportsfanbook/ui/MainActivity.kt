package com.yourself.sportsfanbook.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.ads.AdRequest
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yourself.sportsfanbook.R
import com.yourself.sportsfanbook.data.team.Team
import com.yourself.sportsfanbook.ui.game.TeamGameHistoryFragment
import com.yourself.sportsfanbook.ui.team.ActionBarCallBack
import com.yourself.sportsfanbook.ui.team.TeamListFragment
import com.yourself.sportsfanbook.ui.team.rv.FavouriteItemClickListener
import com.yourself.sportsfanbook.utils.AppAnalytics
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), ActionBarCallBack, FavouriteItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setLogo(R.mipmap.ic_launcher)
        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction().add(
                R.id.container,
                TeamListFragment.newInstance(),
                TeamListFragment.TAG
            ).commitNow()

        val viewModel = ViewModelProvider(this).get(TeamListViewModel::class.java)
        viewModel.selectedTeam.observe(this, teamSelectedObserver)

        //Load Ad's for non-premium uses. Currently All users are non-premium
        val adRequest: AdRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

    }

    private val teamSelectedObserver = Observer<Team> {
        it?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, TeamGameHistoryFragment.newInstance())
                .addToBackStack(null)
                .commit()
            AppAnalytics.selectContent(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showHideActionBarWith(title: String?, showBackButton: Boolean) {
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(showBackButton)
    }

    override fun onFavouriteItemClick(team: Team) {
        AppAnalytics.favouriteEvent(team)
        MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.premium_feature_title))
            .setMessage(getString(R.string.premium_feature_message))
            .setPositiveButton(getString(R.string.alert_dialog_ok)) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }
}
