package com.example.cherish_refactor.data.source.remote.api

import retrofit2.http.*

interface CherishAPI {
        @GET("cherish")
        suspend fun DetailPlant(
            @Query("CherishId") CherishId: Int
        ): PlantDetailResponse


        @GET("cherish/{id}")
        suspend fun getCherishUser(
            @Path("id") userId: Int
        ): UserResult


        @POST("/cherish")
        @Headers("Content-Type:application/json")
        suspend fun enrollCherish(
            @Body enrollCherishReq: PlantEnrollRequest
        ): PlantEnrollResponse


        @GET("user/{id}")
        suspend fun SettingUserPage(
            @Path("id") userId: Int
        ): MyPageUserRes

}