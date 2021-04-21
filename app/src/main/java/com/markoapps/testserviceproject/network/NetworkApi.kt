package com.markoapps.testserviceproject.network


import com.markoapps.testserviceproject.model.LoginRequest
import com.markoapps.testserviceproject.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.Field

import retrofit2.http.POST

interface NetworkApi {

    @POST("052b7ce0-a2ad-11eb-bcc4-a996d3a50772")
    suspend fun login(@Body loginRequest: LoginRequest)
    : LoginResponse



}