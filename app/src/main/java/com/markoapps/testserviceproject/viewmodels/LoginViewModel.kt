package com.markoapps.testserviceproject.viewmodels

import androidx.lifecycle.*
import com.markoapps.testserviceproject.core.Resource
import com.markoapps.testserviceproject.model.LoginRequest
import com.markoapps.testserviceproject.network.NetworkApi
import kotlinx.coroutines.Dispatchers


class LoginViewModel(private val networkApi: NetworkApi): ViewModel() {

    fun login(userName: String, password: String) = liveData(Dispatchers.IO)  {
            try {
                emit(Resource.loading(data = null))
                emit(Resource.success(networkApi.login(LoginRequest(userName, password))))
            } catch (exception : Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
            }
        }
}

