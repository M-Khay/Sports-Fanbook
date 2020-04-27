package com.yourself.sportsfanbook.ui.team.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yourself.sportsfanbook.data.team.Team
import com.yourself.sportsfanbook.databinding.TeamListItemBinding
import com.yourself.sportsfanbook.ui.TeamListViewModel
import kotlinx.android.synthetic.main.team_list_item.view.*

class TeamListAdapter(var teamListViewModel: TeamListViewModel,val favouriteItemClickListener: FavouriteItemClickListener) :
    RecyclerView.Adapter<TeamListViewHolder>() {
    private var teamList = mutableListOf<Team>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamListViewHolder {
        val teamListItemBinding =
            TeamListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamListViewHolder(teamListItemBinding.root, teamListItemBinding)
    }

    override fun onBindViewHolder(holder: TeamListViewHolder, position: Int) {
        holder.setData(teamList[position])
        holder.itemView.favourite_Button.setOnClickListener {
            favouriteItemClickListener.onFavouriteItemClick(teamList[position])
            val favouriteTeam = teamList[position]
            if (holder.itemView.favourite_Button.isChecked) {
                favouriteTeam.favourite = true
                teamListViewModel.saveFavouriteTeam(favouriteTeam)
            } else {
                favouriteTeam.favourite = false
                teamListViewModel.deleteFavouriteTeam(favouriteTeam)
            }
        }
        holder.itemView.setOnClickListener {
            teamListViewModel.setSelectedTeam(teamList[position])
        }
    }

    override fun getItemCount(): Int {
        return teamList.size
    }

    fun updateTeamList(teamList: List<Team>) {
        this.teamList = teamList.toMutableList()
        notifyDataSetChanged()
    }



}
