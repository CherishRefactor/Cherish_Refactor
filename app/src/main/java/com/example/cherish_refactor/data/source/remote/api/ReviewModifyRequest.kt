package com.example.cherish_refactor.data.source.remote.api

import com.google.gson.annotations.SerializedName

data class ReviewModifyRequest(
    @SerializedName("CherishId") val cherishId: Int,
    @SerializedName("water_date") val waterDate: String,
    @SerializedName("review") val review: String?,
    @SerializedName("keyword1") val userStatus1: String?,
    @SerializedName("keyword2") val userStatus2: String?,
    @SerializedName("keyword3") val userStatus3: String?,
)
