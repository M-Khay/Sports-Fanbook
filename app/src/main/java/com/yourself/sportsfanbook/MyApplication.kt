package com.yourself.sportsfanbook

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.yourself.searchyourcityweather.utils.NetworkUtils
import com.yourself.sportsfanbook.di.ComponentInjector
import com.yourself.sportsfanbook.utils.AppAnalytics

// appComponent lives in the Application class to share its lifecycle
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        ComponentInjector.init(applicationContext)

        NetworkUtils.registerNetworkCallback(this)

        AppAnalytics.init(this)

        MobileAds.initialize(this)

    }

}