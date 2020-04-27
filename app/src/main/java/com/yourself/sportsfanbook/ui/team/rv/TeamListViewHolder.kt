package com.yourself.sportsfanbook.ui.team.rv

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yourself.sportsfanbook.data.team.Team
import com.yourself.sportsfanbook.databinding.TeamListItemBinding

class TeamListViewHolder constructor(teamListItemView: View, private val teamListItemBinding: TeamListItemBinding) :
    RecyclerView.ViewHolder(teamListItemView) {

    fun setData(team: Team) {
        teamListItemBinding.teamModel = team
    }

}