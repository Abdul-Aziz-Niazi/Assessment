package com.azeez.assessment.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APISetup {
    private const val BASE_URL = "http://94.206.102.22/app/"

    private fun getAPIClient(): Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.addInterceptor(httpLoggingInterceptor)

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: WebService = getAPIClient().create(WebService::class.java)
}