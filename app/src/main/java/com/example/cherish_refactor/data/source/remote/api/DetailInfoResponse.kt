package com.example.cherish_refactor.data.source.remote.api

data class DetailInfoResponse(
    val data: Data,
    val message: String,
    val success: Boolean
) {
    data class Data(
        val cherishDetail: CherishDetail,
    ) {
        data class CherishDetail(
            val nickname: String,
            val birth: String,
            val phone: String,
            val cycle_date: Int,
            val notice_time: String,
            val water_notice: Boolean
        )

    }
}