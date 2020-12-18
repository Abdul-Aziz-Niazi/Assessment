package com.azeez.assessment.repository

import androidx.lifecycle.MutableLiveData
import com.azeez.assessment.daos.requests.LoginRequest
import com.azeez.assessment.daos.responses.APIResponse
import com.azeez.assessment.daos.responses.AuthResponse
import com.azeez.assessment.daos.responses.ItemResponse
import com.azeez.assessment.network.WebService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BaseRepository(private val service: WebService) {

    fun login(request: LoginRequest): MutableLiveData<APIResponse<AuthResponse>> {
        val data = MutableLiveData<APIResponse<AuthResponse>>()
        service.login(request).enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if (response.isSuccessful) {
                    data.postValue(APIResponse<AuthResponse>().success(response.body()))
                } else {
                    data.postValue(APIResponse<AuthResponse>().failure(null,response.errorBody()?.string()))
                }
            }
            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                data.postValue(APIResponse<AuthResponse>().failure(message = t.message))
            }
        })
        return data
    }

    fun getItems(token:String): MutableLiveData<APIResponse<ItemResponse>> {
        val data = MutableLiveData<APIResponse<ItemResponse>>()
        service.getItems("Bearer $token").enqueue(object : Callback<ItemResponse> {
            override fun onResponse(call: Call<ItemResponse>, response: Response<ItemResponse>) {
                if (response.isSuccessful) {
                    data.postValue(APIResponse<ItemResponse>().success(response.body()))
                } else {
                    data.postValue(APIResponse<ItemResponse>().failure(null,response.errorBody()?.string()))
                }
            }
            override fun onFailure(call: Call<ItemResponse>, t: Throwable) {
                data.postValue(APIResponse<ItemResponse>().failure(message = t.message))
            }
        })
        return data
    }

}