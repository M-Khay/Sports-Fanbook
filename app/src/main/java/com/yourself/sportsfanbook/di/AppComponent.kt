package com.yourself.searchyourcityweather.di

import com.yourself.sportsfanbook.di.AppModule
import com.yourself.sportsfanbook.di.DatabaseModule
import com.yourself.sportsfanbook.di.NetworkModule
import com.yourself.sportsfanbook.di.SportRepositoryModule
import com.yourself.sportsfanbook.ui.TeamListViewModel
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, AppModule::class,
        NetworkModule::class, SportRepositoryModule::class,
        DatabaseModule::class]
)

interface AppComponent {
    fun inject(teamListViewModel: TeamListViewModel)
}


