package com.example.cherish_refactor.data.source.remote.api

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize

data class PlantEnrollResponse(
    val data: Data,
    val message: String,
    val success: Boolean
):Parcelable
    {@Parcelize
    data class Data(
        val plant: Plant
    ):Parcelable

    {
        @Parcelize
        data class Plant(
            val PlantStatusId: Int,
            val explanation: String,
            val flower_meaning: String,
            val id: Int,
            val image_url: String,
            val modifier: String,
            val name: String,
            val thumbnail_image_url: String
        ):Parcelable
    }
}