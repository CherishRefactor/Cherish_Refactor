package com.example.cherish_refactor.data.source.remote.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CherishAPI {
        @GET("cherish")
        suspend fun DetailPlant(
            @Query("CherishId") CherishId: Int
        ): PlantDetailResponse


    @GET("cherish/{id}")
    suspend fun getCherishUser(
        @Path("id") userId: Int
    ): UserResult



    interface MyPageAPI {
        @GET("user/{id}")
        fun fetchUserPage(
            @Path("id") userId: Int
        ): Call<MyPageUserRes>
    }
}