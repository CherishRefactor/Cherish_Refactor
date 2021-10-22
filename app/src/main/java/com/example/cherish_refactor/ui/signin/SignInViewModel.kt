package com.example.cherish_refactor.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cherish_refactor.data.source.remote.api.SignInRequest
import com.example.cherish_refactor.data.source.remote.singleton.RetrofitBuilder
import com.example.cherish_refactor.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class SignInViewModel:BaseViewModel() {


    val email = MutableLiveData<String>()
    val passWord = MutableLiveData<String>()


    private val _isFirst = MutableLiveData<Int>()
    val isFirst : LiveData<Int> = _isFirst

    private val _isAuto = MutableLiveData<Boolean>()
    val isAuto : LiveData<Boolean> = _isAuto

    private val _isSignIn = MutableLiveData<Boolean>()
    val isSignIn : LiveData<Boolean> = _isSignIn

    fun requestLogIn(){
        viewModelScope.launch {
            val body = SignInRequest(email.value!!,passWord.value!!)
            val response = RetrofitBuilder.cherishAPI.postLogin(body)

            //RetrofitBuilder.userAPI.hasUser(userId).enqueue(object : Callback<UserResult>

            if(response.success){
                val userResponse = RetrofitBuilder.cherishAPI.hasUser(response.editUserData.userId)
                _isFirst.value = userResponse.userData.totalUser
            }
            _isSignIn.value=response.success


        }

    }
    fun requestUser(){



    }


}