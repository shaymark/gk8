package com.markoapps.testserviceproject.di

import android.content.Context
import com.markoapps.testserviceproject.network.BaseRetrofit
import com.markoapps.testserviceproject.network.NetworkApi
import com.markoapps.testserviceproject.utils.EncryptionUtil
import com.markoapps.testserviceproject.viewmodels.LoginViewModelFactory

object Provider {

    lateinit var appContext: Context

    val networkApi: NetworkApi by lazy {
        val retrofit = BaseRetrofit().get()
        retrofit.create(NetworkApi::class.java)
    }

    val loginViewModelFactory: LoginViewModelFactory by lazy {
        LoginViewModelFactory(networkApi)
    }

    val encryptionUtil = EncryptionUtil()

    fun inject(appContext: Context) {
        this.appContext = appContext
    }
}