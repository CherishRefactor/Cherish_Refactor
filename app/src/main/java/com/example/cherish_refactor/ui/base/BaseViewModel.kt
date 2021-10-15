package com.example.cherish_refactor.ui.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    protected val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    protected val _isError = MutableLiveData<String>()
    val isError: LiveData<String> = _isError

    protected val _isLogin = MutableLiveData<Boolean>(false)
    val isLogin: LiveData<Boolean> = _isLogin

    protected open fun handleError(tag: String, exception: Exception) {
        Log.e(tag, exception.message ?: "handleError")
        _isLoading.value = false
        _isError.value = exception.message ?: "Error"
    }

    protected fun handleLoading() {
        _isLoading.value = true
    }
}
