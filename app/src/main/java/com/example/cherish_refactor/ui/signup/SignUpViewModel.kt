package com.example.cherish_refactor.ui.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cherish_refactor.data.source.remote.api.SignUpCheckRequest
import com.example.cherish_refactor.data.source.remote.singleton.RetrofitBuilder
import com.example.cherish_refactor.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class SignUpViewModel:BaseViewModel() {


     val email = MutableLiveData<String>()

     val isEmailCheck= MutableLiveData<Boolean>()

    fun requestSignUpEmail(){
        viewModelScope.launch {
            val response = RetrofitBuilder.cherishAPI.postEmail(SignUpCheckRequest(email.value!!))
            isEmailCheck.postValue(response.success)
        }

    }

    fun requestSignUp(){
        viewModelScope.launch {
            /*val body = SignUpRequest(
                email = email,
                password = password,
                nickname = nickName,
                phone = phone,
                sex = sex.toString(),
                birth = birth
            )
            val response = RetrofitBuilder.cherishAPI.postSignUp(body)*/


        }
    }

    fun requestAuth(){
        viewModelScope.launch {

           /* requestData.phoneAuthAPI.postAuth(
                RequestPhoneAuthData(phone = phoneNumber)
            )*/
        }
    }
}