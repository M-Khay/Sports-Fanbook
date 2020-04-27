package com.yourself.sportsfanbook.repository

import com.yourself.sportsfanbook.data.game.GameHistoryListResponse
import com.yourself.sportsfanbook.data.team.TeamListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SportsApi {
    @GET("1/searchteams.php")
    suspend  fun getTeamListFor(@Query("t") teamName: String): TeamListResponse
    @GET("1/eventslast.php")
    suspend  fun getGameHistoryForTeam(@Query("id") teamName: Int): GameHistoryListResponse
}