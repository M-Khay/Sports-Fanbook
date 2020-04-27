package com.yourself.sportsfanbook.di

import android.content.Context
import com.yourself.sportsfanbook.data.database.TeamDao
import com.yourself.sportsfanbook.repository.SportsApi
import com.yourself.sportsfanbook.repository.SportsRepository
import com.yourself.sportsfanbook.repository.SportsRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SportRepositoryModule {
    @Provides @Singleton
    fun provideDictionaryRepository(sportsApi: SportsApi,teamDao: TeamDao): SportsRepository = SportsRepositoryImpl(sportsApi,teamDao)
}