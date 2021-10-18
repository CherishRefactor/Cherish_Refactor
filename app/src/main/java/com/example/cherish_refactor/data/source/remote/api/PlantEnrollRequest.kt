package com.example.cherish_refactor.data.source.remote.api

data class PlantEnrollRequest(
    val name: String,
    val nickname: String,
    val birth: String,
    val phone: String,
    val cycle_date: Int,
    val notice_time: String,
    val water_notice: Boolean,
    val UserId: Int
)
