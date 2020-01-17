package com.example.environmentchallenge.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val _text = MutableLiveData<String>().apply{
        value="This is Login Fragment"
    }

    val name = MutableLiveData<String>()
    var age = MutableLiveData<String>()
    var url = MutableLiveData<String>()

    fun setName(name:String){
        this.name.value = name
    }

    fun setAge(age:String){
        this.age.value = age
    }

    fun setUrl(url:String){
        this.url.value = url
    }

    val text: LiveData<String> = _text
}
