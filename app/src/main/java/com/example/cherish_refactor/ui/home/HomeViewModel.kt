package com.example.cherish_refactor.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cherish_refactor.data.source.remote.api.CalendarResponse
import com.example.cherish_refactor.data.source.remote.api.User
import com.example.cherish_refactor.data.source.remote.singleton.RetrofitBuilder
import com.example.cherish_refactor.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {

    private val _user = MutableLiveData<List<User>>()
    val user: LiveData<List<User>> = _user


    private val _selectedCherishUser = MutableLiveData<User>()
    val selectedCherishUser: LiveData<User> = _selectedCherishUser

    private val _selectedCherishId = MutableLiveData<Int>()
    val selectedCherishId: LiveData<Int> = _selectedCherishId

    private val _calendarData = MutableLiveData<CalendarResponse>()
    val calendarData: LiveData<CalendarResponse> = _calendarData

     val total = MutableLiveData<Int>()

    fun requestMainCherishItem(userId :Int){
        viewModelScope.launch {
            val response = RetrofitBuilder.cherishAPI.getCherishUser(userId)
            _user.postValue(response.userData.userList)
            //userAPI.getCherishUser(userId)

            total.postValue(response.userData.totalUser)
            //setSelectedUser(response.userData.userList[0])
            _selectedCherishId.value=response.userData.userList[0].id
            _selectedCherishUser.postValue(response.userData.userList[0])

            //requestCalendar(response.userData.userList[0].id)
        }
    }

    fun setSelectedUser(user: User) {
        _selectedCherishUser.value=user
        _selectedCherishId.value=user.id

    }

    fun requestCalendar(){
        viewModelScope.launch {
            kotlin.runCatching {
                selectedCherishUser.value?.let { RetrofitBuilder.cherishAPI.getCalendarData(it.id)}
            }.onSuccess {
                _calendarData.postValue(it)
            }

            Log.d("fffff",_selectedCherishId.value.toString())
        }
           // selectedCherishUser.value?.let { RetrofitBuilder.cherishAPI.getCalendarData(it.id)
            //val response= RetrofitBuilder.cherishAPI.getCalendarData(_selectedCherishId.value!!)
                //_calendarData.postValue(response)

        }




    fun requestMainView(id:Int){
    }
}