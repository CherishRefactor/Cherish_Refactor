package com.example.cherish_refactor.data.source.remote.api

import com.google.gson.annotations.SerializedName
import java.util.*

data class CalendarResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val waterData: WaterData
){
    data class WaterData(
        @SerializedName("water") val calendarData: List<CalendarData>,
        @SerializedName("future_water_date") val futureWaterDate: Date
    ){
        data class CalendarData(
            @SerializedName("water_date") val wateredDate: Date,
            @SerializedName("review") val review: String,
            @SerializedName("keyword1") val userStatus1: String,
            @SerializedName("keyword2") val userStatus2: String,
            @SerializedName("keyword3") val userStatus3: String
        )
    }
}



