package com.example.cherish_refactor.ui.enroll

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
          _isCheckPhone.value=false
     }


     fun nextPhone(){
          _isNextPhone.value=true
     }

     fun noNextPhone(){
          _isNextPhone.value=false
     }

     fun requestCheckPhone(phoneNumber : String, userId :Int){

          viewModelScope.launch {

               kotlin.runCatching {
                    val body = CheckPhoneRequest(phone = phoneNumber, UserId = userId)
                    val response = RetrofitBuilder.cherishAPI.checkphone(body)
               }.onSuccess {

                    _isCheckPhone.postValue(true)
               }.onFailure {
                    _isCheckPhone.postValue(false)
               }


          }

     }

}