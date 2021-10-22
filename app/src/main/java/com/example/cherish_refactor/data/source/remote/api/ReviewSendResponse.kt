package com.example.cherish_refactor.data.source.remote.api

import com.google.gson.annotations.SerializedName

data class ReviewSendResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val reviewScore: Int
)
