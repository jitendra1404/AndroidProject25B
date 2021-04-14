package com.example.laptoprepairwear.api

import com.example.laptoprepairwear.Response.LoginResponse
import com.example.laptoprepairwear.entity.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserAPI {

    // register user

    @POST("/user/register")
    suspend fun registerUser(
        @Body user: User
    ): Response<LoginResponse>


    // login user

    @FormUrlEncoded
    @POST("/user/login")
    suspend fun loginUser(
        @Field("custo_name") Username: String,
        @Field("custo_password") password: String

    ): Response<LoginResponse>
}