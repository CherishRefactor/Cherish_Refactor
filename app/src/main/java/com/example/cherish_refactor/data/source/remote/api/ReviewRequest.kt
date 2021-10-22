package com.example.cherish_refactor.data.source.remote.api

import com.google.gson.annotations.SerializedName

data class ReviewRequest(
    @SerializedName("CherishId") val cherishId: Int
)
