package com.macropay.testcoroutins

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//Para ejemplo[5] [42:00]
//Pueden existir problemas para cuando se usa @Test .[50:00]
class MainViewModel:ViewModel()
{
    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> get() = _loginResult

    fun onSubmitClicked(user: String, pass: String){
        viewModelScope.launch {
               _loginResult.value = withContext(Dispatchers.IO) {   validateLogin(user,pass)}
        }
    }

    fun validateLogin(user: String, pass: String): Boolean {
        Thread.sleep(2000)
        return user.isNotEmpty() && pass.isNotEmpty()
    }
}