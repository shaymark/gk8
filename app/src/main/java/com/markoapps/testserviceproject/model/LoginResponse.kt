package com.markoapps.testserviceproject.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginResponse(
        @SerializedName("token") val token: TokenModel,
): Serializable