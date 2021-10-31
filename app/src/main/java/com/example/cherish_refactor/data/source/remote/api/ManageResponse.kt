package com.example.cherish_refactor.data.source.remote.api

import com.google.gson.annotations.SerializedName

data class ManageResponse(
    val myPageUserResponse: UtilResponse,
    @SerializedName("data") val myPageUserData: MyPageUserData
){
    data class MyPageUserData(
        @SerializedName("user_nickname") val user_nickname: String,
        val email: String,
        @SerializedName("postponeCount") val postponeCount: Int,
        @SerializedName("waterCount") val waterCount: Int,
        @SerializedName("completeCount") val completeCount: Int,
        @SerializedName("totalCherish") val totalCherish: Int,
        @SerializedName("result") val result: List<MyPageCherishData>
    )
    {
        data class MyPageCherishData(
            @SerializedName("id") val id: Int,
            @SerializedName("dDay") val dDay: Int,
            @SerializedName("nickname") val nickName: String,
            @SerializedName("name") val name: String,
            @SerializedName("thumbnail_image_url") val thumbnailImageUrl: String,
            @SerializedName("level") val level: Int,
            @SerializedName("PlantId") val plantId: Int
        )
    }

}



