package com.example.cherish_refactor.ui.onboarding

import android.content.Context
import com.example.cherish_refactor.ui.base.BaseViewModel
import com.example.cherish_refactor.util.MySharedPreference

class OnBoardingViewModel(val context : Context):BaseViewModel() {

    fun saveFirstEnter() {
        MySharedPreference.saveFirstEnter(context)
    }

    fun isFirstEnter(): Boolean {
        return MySharedPreference.isFirstEnter(context)
    }


}