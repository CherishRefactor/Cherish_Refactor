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


      val isCheckPhone = MutableLiveData<Boolean>()


     private val _isNextPhone = MutableLiveData<Boolean>()
     val isNextPhone : LiveData<Boolean> = _isNextPhone
     
     init{
          _isNextPhone.value=false
          //_isCheckPhone.value=false
     }


     fun nextPhone(){
         // isCheckPhone.value=next
          _isNextPhone.value=true
     }

     fun noNextPhone(){
          _isNextPhone.value=false
     }

     fun requestCheckPhone(phoneNumber : String, userId :Int){
          viewModelScope.launch {

                    val body = CheckPhoneRequest(phone = phoneNumber, UserId = userId)
                    val response = RetrofitBuilder.cherishAPI.checkphone(body)
                    _isNextPhone.postValue(true)

               kotlin.runCatching {
                    val body = CheckPhoneRequest(phone = phoneNumber, UserId = userId)
                    val response = RetrofitBuilder.cherishAPI.checkphone(body)
               }.onSuccess {

                    _isNextPhone.postValue(true)
               }.onFailure {
                    _isNextPhone.postValue(false)
               }


          }
     }

}