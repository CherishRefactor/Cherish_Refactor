package com.example.cherish_refactor.data.source.remote.api

import com.google.gson.annotations.SerializedName

data class DetailPopUpResponse(
    @SerializedName("sucess") val success: Boolean,
    @SerializedName("data") val data: Data
){
    data class Data(
        @SerializedName("plantResponse") val plantResponse: List<Plant>,
        @SerializedName("plantDetail") val plantDetail: List<PlantDetail>
    )
    {
        data class PlantDetail(
            @SerializedName("description") val description: String,
            @SerializedName("level_name") val level_name: String,
            @SerializedName("image_url") val image_url: String
        )
        data class Plant(
            @SerializedName("explanation") val explanation: String,
            @SerializedName("flower_meaning") val flower_meaning: String,
            @SerializedName("image_url") val image_url: String,
            @SerializedName("modifier") val modifier: String,
        )

    }
}





