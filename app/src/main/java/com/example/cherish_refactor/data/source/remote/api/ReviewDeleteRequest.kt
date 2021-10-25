package com.example.cherish_refactor.data.source.remote.api

import com.google.gson.annotations.SerializedName

data class ReviewDeleteRequest(
    @SerializedName("CherishId") val cherishId: Int,
    @SerializedName("water_date") val waterDate: String
)
