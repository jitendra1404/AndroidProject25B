package com.example.androidproject25b.api

import com.example.androidproject25b.Entity.User
import com.example.androidproject25b.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserAPI {

    // register user

    @POST("register")
    suspend fun registerUser(
            @Body user: User
    ): Response<LoginResponse>


    // login user

    @FormUrlEncoded
    @POST("login")
    suspend fun loginUser(
        @Field("username") username: String,
        @Field("password") password: String


    ):Response<LoginResponse>
}