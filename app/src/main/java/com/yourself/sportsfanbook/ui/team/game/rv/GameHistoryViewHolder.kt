package com.yourself.sportsfanbook.ui.team.rv

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yourself.sportsfanbook.data.game.GameHistory
import com.yourself.sportsfanbook.data.team.Team
import com.yourself.sportsfanbook.databinding.GameHistoryListItemBinding
import com.yourself.sportsfanbook.databinding.TeamListItemBinding

class GameHistoryViewHolder constructor(gameHistoryListItem: View, private val teamListItemBinding: GameHistoryListItemBinding) :
    RecyclerView.ViewHolder(gameHistoryListItem) {

    fun setData(gameHistory: GameHistory) {
        teamListItemBinding.gameHistory = gameHistory
    }

}