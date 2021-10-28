package com.example.cherish_refactor.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cherish_refactor.data.source.remote.api.PhoneAuthRequest
import com.example.cherish_refactor.data.source.remote.api.SignUpCheckRequest
import com.example.cherish_refactor.data.source.remote.api.SignUpRequest
import com.example.cherish_refactor.data.source.remote.singleton.RetrofitBuilder
import com.example.cherish_refactor.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class SignUpViewModel:BaseViewModel() {


     val email = MutableLiveData<String>()

     private val _isEmailCheck= MutableLiveData<Boolean>()
    val isEmailCheck:LiveData<Boolean> = _isEmailCheck

    private val _isAuth= MutableLiveData<Int>()
    val isAuth:LiveData<Int> = _isAuth


    fun requestSignUpEmail(){
        viewModelScope.launch {
            val response = RetrofitBuilder.cherishAPI.postEmail(SignUpCheckRequest(email.value!!))
            _isEmailCheck.postValue(response.success)
        }

    }

    fun requestSignUp(email:String, password:String, nickName:String, phone :String, sex:String,birth:String){
        viewModelScope.launch {
            val body = SignUpRequest(
                email = email,
                password = password,
                nickname = nickName,
                phone = phone,
                sex = sex.toString(),
                birth = birth
            )
            val response = RetrofitBuilder.cherishAPI.postSignUp(body)


        }
    }

    fun requestAuth(phoneNumber:String){
        viewModelScope.launch {

            val response = RetrofitBuilder.cherishAPI.postAuth(PhoneAuthRequest(phoneNumber))
            _isAuth.postValue(response.data)
        }
    }
}