package com.example.cherish_refactor.ui.enroll

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cherish_refactor.data.source.remote.api.PlantEnrollRequest
import com.example.cherish_refactor.data.source.remote.api.PlantEnrollResponse
import com.example.cherish_refactor.data.source.remote.singleton.RetrofitBuilder
import com.example.cherish_refactor.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class EnrollmentViewModel : BaseViewModel() {

    private val _resultPlant = MutableLiveData<PlantEnrollResponse>()
    val resultPlant: LiveData<PlantEnrollResponse> = _resultPlant

     val nickname = MutableLiveData<String>()
     val birth = MutableLiveData<String>()
     val phone = MutableLiveData<String>()
     val date = MutableLiveData<String>()
     val clock = MutableLiveData<String>()

    fun requestnickname(nickName: String, phoneNum:String){
        nickname.value=nickName
        phone.value=phoneNum
    }

    fun requestbirth(birth: String){
        this.birth.value=birth

    }

    fun requestdate(date: String){
        this.date.value=date

    }

    fun requestclock(clock: String){
        this.clock.value=clock

    }

    fun requestEnrollPlant(userid:Int){
        viewModelScope.launch {

            val body =
                PlantEnrollRequest(
                    name = nickname.value!!,
                    nickname = nickname.value!!,
                    birth = birth.value!!,
                    phone = phone.value!!,
                    cycle_date = date.value!!.toInt(),
                    notice_time = clock.value!!,
                    water_notice = true,
                    UserId = userid.toInt()

                )
            var response = RetrofitBuilder.cherishAPI.enrollCherish(body)

            _resultPlant.postValue(response)

        }
    }

}