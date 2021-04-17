package com.example.androidproject25b.Repository

import com.example.androidproject25b.Entity.User
import com.example.androidproject25b.api.MyAPIRequest
import com.example.androidproject25b.api.ServiceBuilder
import com.example.androidproject25b.api.UserAPI
import com.example.androidproject25b.response.LoginResponse

class UserRepository:MyAPIRequest() {

    val userAPI = ServiceBuilder.buildServices(UserAPI::class.java)

    //Register User

    suspend fun registerUser(user: User): LoginResponse {
        return apiRequest {
            userAPI.registerUser(user)
        }
    }
    //login user

    suspend fun loginUser(username: String, password: String): LoginResponse {
        return apiRequest {
            userAPI.loginUser(username, password)
        }
    }
}