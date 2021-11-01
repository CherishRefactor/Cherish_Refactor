package com.example.cherish_refactor.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cherish_refactor.data.source.remote.api.*
import com.example.cherish_refactor.data.source.remote.singleton.RetrofitBuilder
import com.example.cherish_refactor.ui.base.BaseViewModel
import com.example.cherish_refactor.util.DateUtil
import com.prolificinteractive.materialcalendarview.CalendarDay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {

    private val _user = MutableLiveData<List<User>>()
    val user: LiveData<List<User>> = _user

     val userNickName = MutableLiveData<String>()

    private val _selectedCherishUser = MutableLiveData<User>()
    val selectedCherishUser: LiveData<User> = _selectedCherishUser

    private val _manageUser = MutableLiveData<ManageResponse>()
    val manageUser: LiveData<ManageResponse> = _manageUser

    private val _selectedCherishId = MutableLiveData<Int>()
    val selectedCherishId: LiveData<Int> = _selectedCherishId

    private val _calendarData = MutableLiveData<CalendarResponse>()
    val calendarData: LiveData<CalendarResponse> = _calendarData

    private val _isEmptyMemo = MutableLiveData<Boolean>()
    val isEmptyMemo: LiveData<Boolean> = _isEmptyMemo

    val _isCalendarChange = MutableLiveData<Boolean>()
    val isCalendarChange:LiveData<Boolean> = _isCalendarChange

    val isWatered = MutableLiveData<Boolean>()

    val _isRadio = MutableLiveData<Boolean>()
    val isRadio:LiveData<Boolean> = _isRadio

    fun showRadio(){
        _isRadio.value=true
    }

    fun noShowRadio(){
        _isRadio.value=false
    }

    init{
        _isCalendarChange.value=false
        isWatered.value=false
        _isRadio.value=true
    }

    fun showMemo(){

        _isEmptyMemo.value=false
    }

    fun noShowMemo(){
        _isEmptyMemo.value=true
    }

    val selectedDay = MutableLiveData<CalendarResponse.WaterData.CalendarData>()

    val selectedNullDay = MutableLiveData<CalendarDay>()

     val total = MutableLiveData<Int>()

    var selectedFirst = mutableListOf<User>()

    fun requestMainCherishItem(userId :Int){
        viewModelScope.launch {

            if(isWatered.value==true){
                delay(4000)
                val response = RetrofitBuilder.cherishAPI.getCherishUser(userId)
                _user.postValue(response.userData.userList)
                //userAPI.getCherishUser(userId)
                if(response.userData.totalUser!=0){
                    total.postValue(response.userData.totalUser)
                    //setSelectedUser(response.userData.userList[0])

                    _selectedCherishId.value=response.userData.userList[0].id
                    _selectedCherishUser.postValue(response.userData.userList[0])

                    selectedFirst.add(0,response.userData.userList[0])
                    selectedFirst.addAll(response.userData.userList)
                }
                isWatered.value=false

            }else{
                val response = RetrofitBuilder.cherishAPI.getCherishUser(userId)
                _user.postValue(response.userData.userList)
                //userAPI.getCherishUser(userId)

                if(response.userData.totalUser!=0){
                    total.postValue(response.userData.totalUser)
                    //setSelectedUser(response.userData.userList[0])

                    _selectedCherishId.value=response.userData.userList[0].id
                    _selectedCherishUser.postValue(response.userData.userList[0])

                    selectedFirst.add(0,response.userData.userList[0])
                    selectedFirst.addAll(response.userData.userList)
                }

            }


            //user.value[0].
            //requestCalendar(response.userData.userList[0].id)
        }


    }

    fun setSelectedUser(user: User) {
        _selectedCherishUser.value=user
        _selectedCherishId.value=user.id

    }

    fun requestCalendar(cherishId:Int){
        viewModelScope.launch {


               val response = RetrofitBuilder.cherishAPI.getCalendarData(cherishId)
                _calendarData.postValue(response)
                //selectedDay.postValue(response.waterData.calendarData[0])

        }

    }






    fun fetchCalendarData() = viewModelScope.launch(Dispatchers.IO) {
       // Log.d("ffff",selectedCherishUser.value.toString())
        runCatching {
            selectedCherishUser.value?.let {RetrofitBuilder.cherishAPI.getCalendarData(it.id) }
        }.onSuccess {
            _calendarData.postValue(it)
        }.onFailure { error ->
            //errorHandleLivedata.postValue(error.message)
        }
    }

    fun requestReview(){
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val body=ReviewRequest(selectedCherishUser.value!!.id)
                RetrofitBuilder.cherishAPI.sendRemindReviewNotification(body)

            }.onSuccess {
                //SimpleLogger.logI(it.message)
            }.onFailure { error ->
                //errorHandleLivedata.postValue(error.message)
            }
        }

    }

     val memo = MutableLiveData<String>()



    fun sendReviewToServer(chip1:String,chip2:String, chip3:String) = viewModelScope.launch {
        runCatching {
            val body = ReviewSendRequest(memo.value,chip1,chip2,chip3,selectedCherishUser.value!!.id)
            val response = RetrofitBuilder.cherishAPI.reviewWatering(body)
        }.onSuccess {
            //reviewWateringRes = it
        }.onFailure { error ->
           // errorHandleLivedata.value = error.message
        }
    }

    fun sendReviewModify(cherishId:Int,chip1:String,chip2:String, chip3:String,memo:String) = viewModelScope.launch {
        runCatching {
            val date =DateUtil.convertDateToString(
                selectedDay.value?.wateredDate!!
            )
            val body = ReviewModifyRequest(cherishId,date,memo,chip1,chip2,chip3)
            val response = RetrofitBuilder.cherishAPI.reviewModify(body)

        }.onSuccess {
            // Toast를 띄우는건?
           // Toast.makeText(context, "물 줄수있는 날이 아니에요 ㅠ", Toast.LENGTH_SHORT).show()
        }.onFailure { error ->
            //errorHandleLiveData.value = error.message
        }
    }

    fun requestReviewDelete(){
        viewModelScope.launch {
            val date =DateUtil.convertDateToString(
                selectedDay.value?.wateredDate!!
            )
            val response = RetrofitBuilder.cherishAPI.deleteReview(ReviewDeleteRequest(selectedCherishUser.value!!.id,date))
        }
    }


    fun requestManage(id:Int){
        viewModelScope.launch {
            val response = RetrofitBuilder.cherishAPI.fetchMyPage(id)
            _manageUser.postValue(response)
            total.postValue(response.myPageUserData.result.size)

        }
    }

    fun searchPlant(search:String):List<ManageResponse.MyPageUserData.MyPageCherishData>{
        val mulist= mutableListOf<ManageResponse.MyPageUserData.MyPageCherishData>()
        _manageUser.value?.myPageUserData?.result?.forEach {
            if(it.nickName.contains(search)){
                mulist.add(it)
            }
        }
        return mulist
        //_manageUser.value.myPageUserData.result.va(mulist)

    }
}