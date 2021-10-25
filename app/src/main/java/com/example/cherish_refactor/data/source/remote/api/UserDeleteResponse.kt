package com.example.cherish_refactor.data.source.remote.api

import com.google.gson.annotations.SerializedName

data class UserDeleteResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String
)
