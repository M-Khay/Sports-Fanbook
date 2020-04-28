package com.yourself.sportsfanbook.ui.team.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yourself.sportsfanbook.data.game.GameHistory
import com.yourself.sportsfanbook.databinding.GameHistoryListItemBinding
import com.yourself.sportsfanbook.ui.TeamListViewModel

class GameHistoryListAdapter(var teamListViewModel: TeamListViewModel) :
    RecyclerView.Adapter<GameHistoryViewHolder>() {
    private var gameHistoryList = emptyList<GameHistory>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHistoryViewHolder {
        val gameHistoryListItemBinding =
            GameHistoryListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameHistoryViewHolder(gameHistoryListItemBinding.root, gameHistoryListItemBinding)
    }

    override fun onBindViewHolder(holder: GameHistoryViewHolder, position: Int) {
        holder.setData(gameHistoryList[position])
    }

    override fun getItemCount(): Int {
        return gameHistoryList.size
    }

    fun updateGameHistoryList(gameHistoryList: List<GameHistory>) {
        this.gameHistoryList = gameHistoryList
        notifyDataSetChanged()
    }

}
