package com.example.cherish_refactor.data.source.remote.api

import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val editUserData: EditUserData
){
    data class EditUserData(
        @SerializedName("UserId") val userId: Int,
        @SerializedName("user_nickname") val userNickName: String,
        @SerializedName("token") val token: String
    )

}



