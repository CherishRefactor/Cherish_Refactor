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

    @Headers("Content-Type:application/json")
    @PUT("addView")
    suspend fun nicknamechange(
        @Body body: nickNameRequest
    ):nickNameResponse

    @Headers("Content-Type:application/json")
    @GET("getCherishDetail/{CherishId}")
    suspend fun getUserInfo(
        @Path("CherishId") CherishId: Int,
    ): DetailInfoResponse

    @Headers("Content-Type:application/json")
    @PUT("cherish")
    suspend fun plantmodify(
        @Body body: DetailModifyRequest
    ): DetailModifyResponse

    @GET("calendar/{id}")
    suspend fun getCalendarData(
        @Path("id") cherishId: Int
    ): CalendarResponse

    @Headers("Content-Type:application/json")
    @POST("login/signin")
    suspend fun postLogin(
        @Body body: SignInRequest
    ): SignInResponse

    @GET("cherish/{id}")
    suspend fun hasUser(
        @Path("id") userId: Int
    ): UserResult

    @Headers("Content-Type:application/json")
    @DELETE("cherish/{id}")
    suspend fun plantdelete(
        @Path("id") cherishid: Int
    ):PlantDeleteResponse

    @Headers("Content-Type:application/json")
    @POST("cherish/checkPhone")
    suspend fun checkphone(
        @Body body: CheckPhoneRequest
    ): DefaultResponse

    @POST("pushReview")
    suspend fun sendRemindReviewNotification(
        @Body notificationRemindReviewReq: ReviewRequest
    ): UtilResponseWithOutStatus

    @Headers("Content-Type:application/json")
    @POST("water")
    suspend fun reviewWatering(
        @Body reviewWateringReq: ReviewSendRequest
    ): ReviewSendResponse

    @PUT("calendar")
    suspend fun reviewModify(
        @Body reviseReviewReq: ReviewModifyRequest
    ): UtilResponseWithOutStatus

    @POST("checkSameEmail")
    suspend fun postEmail(
        @Body body: SignUpCheckRequest
    ): DefaultResponse

    @POST("login/signup")
    suspend fun postSignUp(
        @Body body: SignUpRequest
    ): SignUpResponse

    @POST("login/phoneAuth")
    suspend fun postAuth(
        @Body body: PhoneAuthResponse
    ): PhoneAuthRequest

}