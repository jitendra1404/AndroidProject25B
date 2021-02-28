package com.example.androidproject25b.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    private const val BASE_URL=  "http://localhost:90/"

    var token :String?=null
    private val okHttp =  OkHttpClient.Builder()

    //Create retrofit Builder

    private val RetrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build())

    //create retrofit instance

    private val retrofit = RetrofitBuilder.build()

    //generic Function

    fun <T> buildServices(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }

}