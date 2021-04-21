package com.markoapps.testserviceproject.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginRequest(
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String
): Serializable