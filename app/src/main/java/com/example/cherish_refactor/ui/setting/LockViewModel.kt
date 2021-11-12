package com.example.cherish_refactor.ui.setting

import androidx.lifecycle.MutableLiveData
import com.example.cherish_refactor.R
import com.example.cherish_refactor.ui.base.BaseViewModel

class LockViewModel :BaseViewModel() {

    val imgPw1 = MutableLiveData<Boolean>()

    val imgPw2 = MutableLiveData<Boolean>()

    val imgPw3 = MutableLiveData<Boolean>()

    val imgPw4 = MutableLiveData<Boolean>()

    val isLockOk = MutableLiveData<Boolean>()

    val isLockBtnOk  = MutableLiveData<Boolean>()
    val isMessageFont: Int
        get() = if(isLockBtnOk.value == true) R.style.RoundButton_GreenMain else R.style.RoundButton_pinkish_Gray
    init{
        imgPw1.value=false
        imgPw2.value=false
        imgPw3.value=false
        imgPw4.value=false
        isLockOk.value=false
        isLockBtnOk.value=false
    }
}