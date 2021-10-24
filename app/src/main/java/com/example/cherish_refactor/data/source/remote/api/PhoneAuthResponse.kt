package com.example.cherish_refactor.data.source.remote.api

import com.google.gson.annotations.SerializedName

data class PhoneAuthResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: Int
)
