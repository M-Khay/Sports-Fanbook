package com.yourself.sportsfanbook.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yourself.sportsfanbook.data.ApiResult
import com.yourself.sportsfanbook.data.Loading
import com.yourself.sportsfanbook.data.Success
import com.yourself.sportsfanbook.data.game.GameHistory
import com.yourself.sportsfanbook.data.team.Team
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
//    var teamList: List<Team> = emptyList()

    var teamGameHistoryListState = MutableLiveData<ApiResult<List<GameHistory>>>()

    init {
        teamListState.value = Success<List<Team>>(
            emptyList(),
            true
        )
    }

    fun setSelectedTeam(selectedTeam: Team) {
        this.selectedTeam.value = selectedTeam
    }


    fun getSelectedTeamName(): String? {
        return selectedTeam.value?.name
    }


    fun getTeamListFor(teamName: String) {
        teamListState.value = Loading(false)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repository.getTeamListWith(teamName)
                withContext(Dispatchers.Main) {
                    teamListState.value =
                        Success<List<Team>>(
                            result.teamListResponse,
                            true
                        )
                }
            } catch (exception: Exception) {
                Log.d(TAG, "Error from API ${exception.localizedMessage}")
                withContext(Dispatchers.Main) {
                    teamListState.value =
                        com.yourself.sportsfanbook.data.Error(
                            exception,
                            true
                        )
                }
            }
        }
    }

    fun getSelectedTeamGameHistory() {
        teamGameHistoryListState.value =
            Loading(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {

                val result = repository.getTeamGameHistory(selectedTeam.value?.id!!)
                withContext(Dispatchers.Main) {
                    teamGameHistoryListState.value =
                        Success<List<GameHistory>>(
                            result.teamGameHistoryResponse,
                            true
                        )
                }

            } catch (exception: Exception) {
                Log.d(TAG, "Error from API ${exception.localizedMessage}")
                withContext(Dispatchers.Main) {
                    teamGameHistoryListState.value =
                        com.yourself.sportsfanbook.data.Error(
                            exception,
                            true
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

     fun getFavouriteTeamList() {
         viewModelScope.launch(Dispatchers.IO) {
             val team = repository.getFavouriteTeam()
             Log.d("SavedTeam", team.toString())
             withContext(Dispatchers.Main) {
                 teamListState.value =
                     Success<List<Team>>(team, false)
             }
         }
     }
}
