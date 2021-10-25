package com.example.cherish_refactor.data.source.remote.api

import com.google.gson.annotations.SerializedName

data class PostponeWateringResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val postponeData: PostponeWateringData
){
    data class PostponeWateringData(
        @SerializedName("cherish") val wateredDateAndPostponeCount: WateredDateAndPostponeCount,
        @SerializedName("is_limit_postpone_number") val isPostpone: Boolean
    ){
        data class WateredDateAndPostponeCount(
            @SerializedName("water_date") val waterDate: String,
            @SerializedName("postpone_number") val postponeCount: Int
        )
    }
}
