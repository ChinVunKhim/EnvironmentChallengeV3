package com.example.environmentchallenge.database.challenge

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Challenge::class], version = 1, exportSchema = true)
abstract class ChallengeDatabase : RoomDatabase() {

    abstract val challengeDatabaseDAO : ChallengeDAO

    companion object {

        @Volatile
        private var INSTANCE: ChallengeDatabase? = null

        fun getInstance(context: Context): ChallengeDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ChallengeDatabase::class.java,
                        "challenge_database"
                    )
                        .allowMainThreadQueries().fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}