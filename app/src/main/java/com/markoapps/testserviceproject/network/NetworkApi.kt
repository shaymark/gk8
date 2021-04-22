package com.markoapps.testserviceproject.network


import com.markoapps.testserviceproject.model.LoginRequest
import com.markoapps.testserviceproject.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.Field

import retrofit2.http.POST

interface NetworkApi {

    @POST("19e676f-a336-11eb-8efc-d9e3bf61bc9d")
    suspend fun login(@Body loginRequest: LoginRequest)
    : LoginResponse



}