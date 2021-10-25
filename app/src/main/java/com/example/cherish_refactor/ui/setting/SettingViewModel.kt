package com.example.cherish_refactor.ui.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cherish_refactor.data.source.remote.api.MyPageUserRes
import com.example.cherish_refactor.data.source.remote.api.UserDeleteRequest
import com.example.cherish_refactor.data.source.remote.api.nickNameRequest
import com.example.cherish_refactor.data.source.remote.singleton.RetrofitBuilder
import com.example.cherish_refactor.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class SettingViewModel :BaseViewModel() {

    private val _user=MutableLiveData<MyPageUserRes>()
    var user:LiveData<MyPageUserRes> = _user

    val nickName = MutableLiveData<String>()

    val email = MutableLiveData<String>()


    fun requestSettingUser(userId :Int){
        viewModelScope.launch {
            val response=RetrofitBuilder.cherishAPI.SettingUserPage(userId)
            _user.postValue(response)
            nickName.postValue(response.myPageUserData.user_nickname)
            email.postValue(response.myPageUserData.email)
        }
    }

    fun requestNickNameChange(userId: Int){
        viewModelScope.launch {

            val body = nickNameRequest(
                userId,
                nickName.value!!
            )
            val response = RetrofitBuilder.cherishAPI.nicknamechange(body)
            if(response.success){
                nickName.value=nickName.value
            }

        }
    }

    fun requestUserDelete(userId: Int){
        viewModelScope.launch {
            val response = RetrofitBuilder.cherishAPI.deleteUser(UserDeleteRequest(userId))
        }

    }
}