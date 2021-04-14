package com.example.laptoprepairwear.Repository

import com.example.laptoprepairwear.Response.LoginResponse
import com.example.laptoprepairwear.api.MyAPIRequest
import com.example.laptoprepairwear.api.ServiceBuilder
import com.example.laptoprepairwear.api.UserAPI

class UserRepository :
    MyAPIRequest() {

    val userAPI = ServiceBuilder.buildServices(UserAPI::class.java)

    //login user

    suspend fun loginUser(username: String, password: String): LoginResponse {
        return apiRequest {
            userAPI.loginUser(username, password)
        }
    }
}