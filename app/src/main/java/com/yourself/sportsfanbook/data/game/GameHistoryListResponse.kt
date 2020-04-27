package com.yourself.sportsfanbook.data.game

import com.google.gson.annotations.SerializedName
import com.yourself.sportsfanbook.data.team.Team

data class GameHistoryListResponse(
    @SerializedName("results")
    var teamGameHistoryResponse: List<GameHistory>
)