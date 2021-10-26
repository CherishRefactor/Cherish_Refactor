package com.example.cherish_refactor.data.source.remote.api

import com.google.gson.annotations.SerializedName

data class PwFindResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("data") val data: VerifyCode
){
    data class VerifyCode(
        @SerializedName("verifyCode") val verifyCode: Int
    )

}
