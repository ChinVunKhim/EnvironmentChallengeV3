package com.example.environmentchallenge.ui.logout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LogoutViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val _text = MutableLiveData<String>().apply{
        value ="This is Logout Fragment"
    }

    val text: LiveData<String> = _text
}
