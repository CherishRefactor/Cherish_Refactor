package com.example.cherish_refactor.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cherish_refactor.data.mapper.DataToEntity
import com.example.cherish_refactor.data.source.remote.api.DetailModifyRequest
import com.example.cherish_refactor.data.source.remote.singleton.RetrofitBuilder
import com.example.cherish_refactor.domain.entity.PlantDetail
import com.example.cherish_refactor.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException

class DetailPlantViewModel : BaseViewModel() {

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    private val _plantDetail = MutableLiveData<PlantDetail>()
    val plantDetail: LiveData<PlantDetail> = _plantDetail

    val nickname = MutableLiveData<String>()
    val birth = MutableLiveData<String>()
    val phone = MutableLiveData<String>()
    val date = MutableLiveData<String>()
    val clock = MutableLiveData<String>()

    private val _gage = MutableLiveData<Int>()
    val gage : LiveData<Int> = _gage

    private val _isEmptyMemo = MutableLiveData<Boolean>()
    val isEmptyMemo : LiveData<Boolean> = _isEmptyMemo

    private val _isEmptyMemo2 = MutableLiveData<Boolean>()
    val isEmptyMemo2 : LiveData<Boolean> = _isEmptyMemo2

     val _isTouch = MutableLiveData<Boolean>()
    //val isTouch : LiveData<Boolean> = _isTouch

    init{
        _isTouch.value=false
    }


    fun requestPlantDetail(cherishId: Int) {
        viewModelScope.launch {
            try{
                val response = RetrofitBuilder.cherishAPI.DetailPlant(cherishId)
                _plantDetail.postValue(DataToEntity.PlantDetail(response.data))
                _gage.postValue((response.data.gage*100).toInt())
                if(response.data.reviews.size==0){
                    _isEmptyMemo.postValue(true)
                    _isEmptyMemo2.postValue(true)
                }else if(response.data.reviews.size==1){
                    _isEmptyMemo.postValue(false)
                    _isEmptyMemo2.postValue(true)
                } else{
                    _isEmptyMemo.postValue(false)
                    _isEmptyMemo2.postValue(false)
                }



            }catch (e:HttpException){

            }

            //_plantDetail.postValue(DataToEntity.PlantDetail)
        }

    }

    fun requestModifyView(cherishId: Int){

        viewModelScope.launch {
            val response = RetrofitBuilder.cherishAPI.getUserInfo(cherishId)
            nickname.value=response.data.cherishDetail.nickname
            if(response.data.cherishDetail.birth=="Invalid Date"){
                birth.value="00/00"
            }else{
                birth.value=response.data.cherishDetail.birth.split("-")[1] + "/" +response.data.cherishDetail.birth.split("-")[2]
            }

            date.value="Every " + response.data.cherishDetail.cycle_date.toString() + " day"

            if (response.data.cherishDetail.notice_time.split(":")[0].toInt() < 12) {
                clock.value = response.data.cherishDetail.notice_time
            } else {
                clock.value = response.data.cherishDetail.notice_time
            }
            phone.value=response.data.cherishDetail.phone

        }

    }

    fun requestModifyDetail(cherishId: Int){

        viewModelScope.launch {
            var body = DetailModifyRequest(
                nickname = nickname.value!!,
                birth = birth.value!!,
                cycle_date = date.value!!.split(" ")[1].toInt(),
                notice_time = clock.value!!,
                water_notice = true,
                id = cherishId
            )

           val response =  RetrofitBuilder.cherishAPI.plantmodify(body)
        }
    }

    fun requestPlantDelete(cherishId: Int){
        viewModelScope.launch {
            val response = RetrofitBuilder.cherishAPI.plantdelete(cherishId)
        }

    }


}