package com.yourself.sportsfanbook.repository

import com.yourself.sportsfanbook.data.game.GameHistoryListResponse
import com.yourself.sportsfanbook.data.team.Team
import com.yourself.sportsfanbook.data.team.TeamListResponse

interface SportsRepository {
    suspend fun getTeamListWith(teamName: String): TeamListResponse
    suspend fun getGameHistoryForTeam(teamId: Int): GameHistoryListResponse
    suspend fun saveFavouriteTeam(team: Team)
    suspend fun deleteFavouriteTeam(team: Team)
}