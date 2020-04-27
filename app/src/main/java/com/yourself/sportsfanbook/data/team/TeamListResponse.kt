package com.yourself.sportsfanbook.data.team

import com.google.gson.annotations.SerializedName
import com.yourself.sportsfanbook.data.team.Team

data class TeamListResponse(
    @SerializedName("teams")
    var teamListResponse: List<Team>
)