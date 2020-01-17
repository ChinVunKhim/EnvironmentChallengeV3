package com.example.environmentchallenge.ui.weekly_challenge

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.environmentchallenge.MainActivity
import com.example.environmentchallenge.database.challenge.Challenge

class WeeklyChallengeViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    val c = MainActivity.challengeDb.challengeDatabaseDAO.getRandom()
    private val _text = MutableLiveData<String>().apply {
        value = c?.challengeName + "\n\n"  +c?.challengeDesc
    }



    val text: LiveData<String> = _text
    var color:Int= Color.WHITE
    var count = 0
    var doneButton:Boolean=true
    fun addProgress(): Int {
        count += 1
        return count
    }

    fun getCurrentProgess(): Int {
        return count;
    }

    fun getText(): String {
        return count.toString() + "/" + 7

    }

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

    fun changeChallenge(): Challenge?
    {
        return MainActivity.challengeDb.challengeDatabaseDAO.getRandom()
    }
}
