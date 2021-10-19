package com.example.cherish_refactor.ui.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cherish_refactor.data.source.remote.api.MyPageUserRes
import com.example.cherish_refactor.data.source.remote.singleton.RetrofitBuilder
import com.example.cherish_refactor.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class SettingViewModel :BaseViewModel() {

    private val _user=MutableLiveData<MyPageUserRes>()
    var user:LiveData<MyPageUserRes> = _user


    fun requestSettingUser(userId :Int){
        viewModelScope.launch {
            val response=RetrofitBuilder.cherishAPI.SettingUserPage(userId)
            _user.postValue(response)
        }
    }
}