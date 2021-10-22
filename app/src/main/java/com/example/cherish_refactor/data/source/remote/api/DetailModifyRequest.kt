package com.example.cherish_refactor.data.source.remote.api

data class DetailModifyRequest(
    val nickname: String,
    val birth: String,
    val cycle_date: Int,
    val notice_time: String,
    val water_notice: Boolean,
    val id: Int
)

