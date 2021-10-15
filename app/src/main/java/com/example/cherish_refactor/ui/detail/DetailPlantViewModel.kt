package com.example.cherish_refactor.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cherish_refactor.data.mapper.DataToEntity
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


    fun requestPlantDetail(cherishId: Int) {
        viewModelScope.launch {
            try{
                val response = RetrofitBuilder.cherishAPI.DetailPlant(cherishId)
                _plantDetail.postValue(DataToEntity.PlantDetail(response.data))

            }catch (e:HttpException){

            }

            //_plantDetail.postValue(DataToEntity.PlantDetail)
        }

    }

}