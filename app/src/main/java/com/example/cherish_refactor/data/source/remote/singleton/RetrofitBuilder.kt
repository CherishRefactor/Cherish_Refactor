package com.example.cherish_refactor.data.source.remote.singleton

import com.example.cherish_refactor.data.source.remote.api.CherishAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {
    private const val BaseUrl = "http://cherishserver.com/"

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkhttpClient())
            .build()
    }

    private fun getOkhttpClient() = OkHttpClient.Builder().apply {

        //TimeOut 시간을 지정합니다.
        readTimeout(60, TimeUnit.SECONDS)
        connectTimeout(60, TimeUnit.SECONDS)
        writeTimeout(5, TimeUnit.SECONDS)

        // 이 클라이언트를 통해 오고 가는 네트워크 요청/응답을 로그로 표시하도록 합니다.
        addInterceptor(getLoggingInterceptor())

    }.build()

    private fun getLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }


    val cherishAPI: CherishAPI = getRetrofit().create(CherishAPI::class.java)


}
