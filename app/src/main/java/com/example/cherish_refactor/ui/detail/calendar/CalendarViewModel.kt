package com.example.cherish_refactor.ui.detail.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cherish_refactor.data.source.remote.api.CalendarResponse
import com.example.cherish_refactor.data.source.remote.singleton.RetrofitBuilder
import com.example.cherish_refactor.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class CalendarViewModel:BaseViewModel() {



    private val _calendarData = MutableLiveData<CalendarResponse>()
    val calendarData: LiveData<CalendarResponse> = _calendarData


     val selectedDay = MutableLiveData<CalendarResponse.WaterData.CalendarData>()
    //val selectedDay: LiveData<CalendarResponse.WaterData.CalendarData> = _selectedDay



    fun requestCalendar(cherishId:Int){
        viewModelScope.launch {

                val response = RetrofitBuilder.cherishAPI.getCalendarData(cherishId)

                _calendarData.postValue(response)


        }
        // selectedCherishUser.value?.let { RetrofitBuilder.cherishAPI.getCalendarData(it.id)
        //val response= RetrofitBuilder.cherishAPI.getCalendarData(_selectedCherishId.value!!)
        //_calendarData.postValue(response)

    }
}