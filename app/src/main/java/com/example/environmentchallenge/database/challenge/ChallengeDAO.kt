package com.example.environmentchallenge.database.challenge

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ChallengeDAO {

    @Insert
    fun insert(challenge: Challenge)

    @Update
    fun update(challenge: Challenge)

    @Query("SELECT * from challenge_table WHERE challengeId = :key")
    fun get(key: String): Challenge?

    @Query("SELECT * from challenge_table ORDER BY RANDOM() LIMIT 1")
    fun getRandom(): Challenge?

    @Query("DELETE from challenge_table")
    fun clear()

}