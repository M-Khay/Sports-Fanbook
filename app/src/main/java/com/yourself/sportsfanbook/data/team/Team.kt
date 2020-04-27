package com.yourself.sportsfanbook.data.team

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "word_table")
data class Team(
    @PrimaryKey @ColumnInfo(name = "id")
    @SerializedName("idTeam")
    var id: Int,

    @ColumnInfo(name = "name")
    @SerializedName("strTeam")
    var name: String,

    @ColumnInfo(name = "sport")
    @SerializedName("strSport")
    var sport: String,

    @ColumnInfo(name="favourite")
    var favourite: Boolean = false
)
