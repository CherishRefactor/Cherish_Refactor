package com.example.cherish_refactor.ui.pwfind

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cherish_refactor.data.source.remote.api.PwFindRequest
import com.example.cherish_refactor.data.source.remote.api.PwUpdateRequest
import com.example.cherish_refactor.data.source.remote.singleton.RetrofitBuilder
import com.example.cherish_refactor.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class PwFindViewModel : BaseViewModel() {

    val _isData = MutableLiveData<Int>()
    val isData :LiveData<Int> = _isData


    fun requestServer(email:String){
        viewModelScope.launch {
            val response = RetrofitBuilder.cherishAPI.postPwFinding(PwFindRequest(email = email))
            _isData.postValue(response.data.verifyCode)

        }
    }

    fun requestUpdatePw(email:String , password1:String,password2:String){
        viewModelScope.launch {
            val response = RetrofitBuilder.cherishAPI.postPwFUpdate(PwUpdateRequest(email,password1,password2))

        }
    }
}