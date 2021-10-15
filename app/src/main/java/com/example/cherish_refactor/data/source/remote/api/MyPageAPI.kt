package com.example.cherish_refactor.data.source.remote.api

import com.google.gson.annotations.SerializedName

// Request param id
// MyPage 조회
data class MyPageUserData(
    @SerializedName("user_nickname") val user_nickname: String,
    val email: String,
    @SerializedName("postponeCount") val postponeCount: Int,
    @SerializedName("waterCount") val waterCount: Int,
    @SerializedName("completeCount") val completeCount: Int,
    @SerializedName("totalCherish") val totalCherish: Int,
    @SerializedName("result") val result: List<MyPageCherishData>
)

data class MyPageUserRes(
    val myPageUserResponse: UtilResponse,
    @SerializedName("data") val myPageUserData: MyPageUserData
)

data class MyPageCherishData(
    @SerializedName("id") val id: Int,
    @SerializedName("dDay") val dDay: Int,
    @SerializedName("nickname") val nickName: String,
    @SerializedName("name") val name: String,
    @SerializedName("thumbnail_image_url") val thumbnailImageUrl: String,
    @SerializedName("level") val level: Int,
    @SerializedName("PlantId") val plantId: Int
)

