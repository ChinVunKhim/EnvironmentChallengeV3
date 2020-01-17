package com.example.environmentchallenge.database.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="user_table")
data class User(
    @PrimaryKey
    val userId: String,

    @ColumnInfo(name="user_name")
    val userName: String,

    @ColumnInfo(name="user_points")
    val userPoints: Int
)