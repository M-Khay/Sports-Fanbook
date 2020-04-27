package com.yourself.sportsfanbook.data.game

import com.google.gson.annotations.SerializedName


data class GameHistory(
    @SerializedName("idEvent") var id: Int,
    @SerializedName("strHomeTeam") var homeTeam: String,
    @SerializedName("strAwayTeam") var awayTeam: String,
    @SerializedName("intHomeScore") var homeTeamScore: Int?,
    @SerializedName("intAwayScore") var awayTeamScore: Int?,
    @SerializedName("dateEvent") var date: String?

)