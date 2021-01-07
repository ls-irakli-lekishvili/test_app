package com.example.jwtconnection.services

import com.example.jwtconnection.Constants
import com.example.jwtconnection.models.AuthModel
import com.example.jwtconnection.models.ResponseModel
import com.example.jwtconnection.models.Test
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET(Constants.GET_POST)
    fun getPosts(): Call<Test>

    @POST(Constants.LOGIN_URL)
    fun login(@Body request: AuthModel): Call<ResponseModel>
}
