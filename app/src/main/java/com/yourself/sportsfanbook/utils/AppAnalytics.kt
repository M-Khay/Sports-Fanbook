package com.yourself.sportsfanbook.utils

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.yourself.sportsfanbook.data.team.Team


object AppAnalytics {
    private var mFirebaseAnalytics: FirebaseAnalytics? = null

    fun init(context: Context) {
        if (mFirebaseAnalytics == null) {
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(context)
        }
    }

    fun searchEvent(searchTerm: String) {
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.CONTENT, searchTerm)
        mFirebaseAnalytics?.logEvent(FirebaseAnalytics.Event.SEARCH, bundle)
    }

    fun favouriteEvent(team: Team) {
        val bundle = Bundle()
        bundle.putString(Constant.FAVOURITE_TEAM, team.name)
        bundle.putString(Constant.FAVOURITE_SPORT, team.sport)
        mFirebaseAnalytics?.logEvent(Constant.FAVOURITE_EVENT, bundle)
    }

    fun selectContent(team: Team) {
        val bundle = Bundle()
        bundle.putString(Constant.SELECT_TEAM_NAME, team.name)
        bundle.putString(Constant.SELECT_TEAM_SPORT, team.sport)
        mFirebaseAnalytics?.logEvent(Constant.SELECTED_EVENT, bundle)
    }

}

