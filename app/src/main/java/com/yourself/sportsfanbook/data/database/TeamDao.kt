package com.yourself.sportsfanbook.data.database

import androidx.room.*
import com.yourself.sportsfanbook.data.team.Team

@Dao
interface TeamDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(team: Team)

    @Delete
    suspend fun delete(team:Team)

    @get:Query("Select * from word_table ORDER BY name ASC")
    val result : List<Team>

}