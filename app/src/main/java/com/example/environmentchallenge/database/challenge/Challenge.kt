package com.example.environmentchallenge.database.challenge

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "challenge_table")
data class Challenge(
    @PrimaryKey
    var challengeId: String,

    @ColumnInfo(name="challenge_name")
    var challengeName: String,

    @ColumnInfo(name="challenge_desc")
    var challengeDesc: String
)