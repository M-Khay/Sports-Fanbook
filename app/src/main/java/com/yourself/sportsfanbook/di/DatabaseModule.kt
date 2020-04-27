package com.yourself.sportsfanbook.di

import android.app.Application
import android.content.Context
import com.yourself.sportsfanbook.data.database.TeamDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton


@Module
class DatabaseModule(private var context: Context) {


    @Provides
    fun provideTeamDao() = TeamDatabase.getInstance(context).teamDao()


}