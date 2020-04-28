package com.yourself.sportsfanbook.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yourself.sportsfanbook.repository.ApiResult
import com.yourself.sportsfanbook.repository.Loading
import com.yourself.sportsfanbook.repository.Success
import com.yourself.sportsfanbook.data.game.GameHistory
import com.yourself.sportsfanbook.data.team.Team
import com.yourself.sportsfanbook.repository.Error
import com.yourself.sportsfanbook.repository.SportsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TeamListViewModel : ViewModel() {
    private val TAG = TeamListViewModel::class.java.name

    @Inject
    lateinit var repository: SportsRepository

    var selectedTeam = MutableLiveData<Team>()
    var teamListState = MutableLiveData<ApiResult<List<Team>>>()
    var teamGameHistoryListState = MutableLiveData<ApiResult<List<GameHistory>>>()

    init {
        teamListState.value =
            Success(emptyList(), true)
    }

    fun setSelectedTeam(selectedTeam: Team) {
        this.selectedTeam.value = selectedTeam
    }

    fun getSelectedTeamName(): String? {
        return selectedTeam.value?.name
    }


    fun getTeamListFor(teamName: String) {
        teamListState.value = Loading(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repository.getTeamListWith(teamName)
                withContext(Dispatchers.Main) {
                    teamListState.value =
                        Success(
                            result.teamListResponse,
                            false
                        )
                }
            } catch (exception: Exception) {
                Log.d(TAG, "Error from API ${exception.localizedMessage}")
                withContext(Dispatchers.Main) {
                    teamListState.value =
                        Error(
                            exception,
                            false
                        )
                }
            }
        }
    }

    fun getSelectedTeamGameHistory() {
        teamGameHistoryListState.value = Loading(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repository.getGameHistoryForTeam(selectedTeam.value?.id!!)
                withContext(Dispatchers.Main) {
                    teamGameHistoryListState.value =
                        Success(
                            result.teamGameHistoryResponse,
                            false
                        )
                }
            } catch (exception: Exception) {
                Log.d(TAG, "Error from API ${exception.localizedMessage}")
                withContext(Dispatchers.Main) {
                    teamGameHistoryListState.value =
                        Error(
                            exception,
                            false
                        )
                }
            }
        }
    }

    fun saveFavouriteTeam(team: Team) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveFavouriteTeam(team)
        }
    }

    fun deleteFavouriteTeam(team: Team) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFavouriteTeam(team)
        }
    }

}
