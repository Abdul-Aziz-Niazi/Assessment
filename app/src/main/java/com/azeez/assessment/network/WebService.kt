package com.azeez.assessment.network

import com.azeez.assessment.daos.requests.LoginRequest
import com.azeez.assessment.daos.responses.AuthResponse
import com.azeez.assessment.daos.responses.ItemResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface WebService {
    @POST("authenticate")
    fun login(@Body request: LoginRequest): Call<AuthResponse>

    @POST("item-list")
    fun getItems(@Header("Authorization") token: String): Call<ItemResponse>
}