package com.markoapps.testserviceproject.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TokenModel(
    @SerializedName("value") val value: String,
): Serializable {
    companion object {
        val EMPTY = TokenModel("")
    }
}