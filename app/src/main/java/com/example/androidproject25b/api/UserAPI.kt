package com.example.androidproject25b.api

import com.example.androidproject25b.Entity.User

interface UserAPI {

    // register user

    @POST("auth/register")
    suspend fun registerUser(
            @Body user: User
    ):Response<LoginResponse>


    // login user

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun loginUser(
            @Field("username") username: String,
            @Field("password") password: String


    ):Response<LoginResponse>
}