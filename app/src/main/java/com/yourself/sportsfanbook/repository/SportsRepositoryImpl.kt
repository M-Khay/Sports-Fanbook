package com.yourself.sportsfanbook.repository

import com.yourself.sportsfanbook.data.database.TeamDao
import com.yourself.sportsfanbook.data.game.GameHistoryListResponse
import com.yourself.sportsfanbook.data.team.Team
import com.yourself.sportsfanbook.data.team.TeamListResponse

class SportsRepositoryImpl(private val sportsApi: SportsApi,private val teamDao: TeamDao)  : SportsRepository {
    override suspend fun getTeamListWith(teamName: String): TeamListResponse = sportsApi.getTeamListFor(teamName)
    override suspend fun getGameHistoryForTeam(teamId: Int): GameHistoryListResponse = sportsApi.getGameHistoryForTeam(teamId)
    override suspend fun saveFavouriteTeam(team: Team)= teamDao.insert(team)
    override suspend fun deleteFavouriteTeam(team: Team)= teamDao.delete(team)
}