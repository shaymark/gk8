package com.markoapps.testserviceproject.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BaseRetrofit {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonblob.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun get() = retrofit

}