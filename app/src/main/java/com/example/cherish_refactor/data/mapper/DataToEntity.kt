package com.example.cherish_refactor.data.mapper

import com.example.cherish_refactor.data.source.remote.api.PlantDetailResponse
import com.example.cherish_refactor.domain.entity.PlantDetail

object DataToEntity {

    fun PlantDetail(plantDetailResponse: PlantDetailResponse.Data) = PlantDetail(
        birth = plantDetailResponse.birth,
        name = plantDetailResponse.name,
        dDay = plantDetailResponse.dDay,
        duration = plantDetailResponse.duration,
        phone = plantDetailResponse.phone,
        status = plantDetailResponse.status,
        status_message = plantDetailResponse.status_message,
        plantId = plantDetailResponse.plantId,
        plant_name = plantDetailResponse.plant_name,
        gage = plantDetailResponse.gage,
        keyword1 = plantDetailResponse.keyword1,
        keyword2 = plantDetailResponse.keyword2,
        keyword3 = plantDetailResponse.keyword3,
        plant_thumbnail_image_url = plantDetailResponse.plant_thumbnail_image_url,
        nickname = plantDetailResponse.nickname,
        reviews = plantDetailResponse.reviews.map {
            PlantDetail.Review(
                review = it.review,
                water_date = it.water_date
            )
        }
    )

    fun PlantDetail.map() = PlantDetailResponse.Data(
        birth = this.birth,
        name = this.name,
        dDay = this.dDay,
        duration = this.duration,
        phone = this.phone,
        status = this.status,
        status_message = this.status_message,
        plantId = this.plantId,
        plant_name = this.plant_name,
        gage = this.gage,
        keyword1 = this.keyword1,
        keyword2 = this.keyword2,
        keyword3 = this.keyword3,
        plant_thumbnail_image_url = this.plant_thumbnail_image_url,
        nickname = this.nickname,
        reviews = this.reviews.map {
            PlantDetailResponse.Data.Review(
                review = it.review,
                water_date = it.water_date
            )
        }
    )
}