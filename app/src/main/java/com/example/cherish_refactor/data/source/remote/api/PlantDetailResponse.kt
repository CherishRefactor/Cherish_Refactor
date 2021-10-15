package com.example.cherish_refactor.data.source.remote.api

data class PlantDetailResponse(
    val data: Data,
    val message: String,
    val success: Boolean
) {
    data class Data(
        val birth: String,
        val dDay: Int,
        val duration: Int,
        val gage: Float,
        val keyword1: String,
        val keyword2: String,
        val keyword3: String,
        val name: String,
        val nickname: String,
        val phone: String,
        val plant_name: String,
        val plant_thumbnail_image_url: String,
        val plantId: Int,
        val reviews: List<Review>,
        val status_message: String,
        val status: String
    ) {
        data class Review(
            val review: String,
            val water_date: String
        )
    }
}
