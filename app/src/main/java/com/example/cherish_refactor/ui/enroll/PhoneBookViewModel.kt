package com.example.cherish_refactor.ui.enroll

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cherish_refactor.data.source.remote.api.CheckPhoneRequest
import com.example.cherish_refactor.data.source.remote.singleton.RetrofitBuilder
import com.example.cherish_refactor.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class PhoneBookViewModel :BaseViewModel() {


     val name = MutableLiveData<String>()
     val phone = MutableLiveData<String>()


     private val _isCheckPhone = MutableLiveData<Boolean>()
     val isCheckPhone : LiveData<Boolean> = _isCheckPhone

     private val _isNextPhone = MutableLiveData<Boolean>()
     val isNextPhone : LiveData<Boolean> = _isNextPhone
     
     init{
          _isNextPhone.value=false
     }


     fun nextPhone(){
          _isNextPhone.value=true
     }

     fun noNextPhone(){
          _isNextPhone.value=false
     }

     fun requestCheckPhone(phoneNumber : String, userId :Int){

          viewModelScope.launch {
               runCatching {
                    val body = CheckPhoneRequest(phone = phoneNumber, UserId = userId)
                    val response = RetrofitBuilder.cherishAPI.checkphone(body)
               }.onSuccess {
                    Log.d("dddd",it.toString())
                    _isCheckPhone.postValue(false)
               }.onFailure {
                    _isCheckPhone.postValue(true)
               }


          }

     }

}