package com.example.environmentchallenge.ui.daily_challenge

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.environmentchallenge.MainActivity
import com.example.environmentchallenge.database.challenge.Challenge
import kotlin.random.Random

class DailyChallengeViewModel : ViewModel() {
    val c = MainActivity.challengeDb.challengeDatabaseDAO.getRandom()
    private val _text = MutableLiveData<String>().apply {
        value = c?.challengeName + "\n\n"  +c?.challengeDesc
    }
    val text: LiveData<String> = _text
    var doneButton:Boolean=true
    var color:Int= Color.WHITE



    fun isDone():Boolean
    {
        doneButton=false
        color= Color.LTGRAY
        return doneButton
    }

    fun notDone():Boolean
    {
        doneButton=true
        color= Color.WHITE
        return doneButton
    }

    fun changeChallenge():Challenge?
    {
        return MainActivity.challengeDb.challengeDatabaseDAO.getRandom()
    }


}
