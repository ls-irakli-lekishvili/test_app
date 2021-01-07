package com.example.jwtconnection.models

import com.google.gson.annotations.SerializedName

data class ResponseModel(
    @SerializedName("jwt")
    val jwt: String
)