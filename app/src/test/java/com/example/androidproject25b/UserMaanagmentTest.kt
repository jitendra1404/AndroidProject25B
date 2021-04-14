package com.example.androidproject25b

import com.example.androidproject25b.Entity.User
import com.example.androidproject25b.Repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class UserMaanagmentTest {

    private lateinit var userRepository: UserRepository

    //...........................USER login and Register Testing...........................//

    @Test

    fun checkLogin() = runBlocking {
        userRepository = UserRepository()
        val response=userRepository.loginUser("kishan", "kishan")
        val ExpectedResult=true
        val ActualResult=response.success
        Assert.assertEquals(ExpectedResult, ActualResult)
    }

    @Test

    fun checkRegister() = runBlocking {

       val user = User(custo_name = "kishan1234", custo_password = "kishan", custo_email ="kishan9999@gmail.com", custo_mobile = "9807278869", custo_address = "Jaipur")

        userRepository = UserRepository()
        val response =userRepository.registerUser(user)
        val ExpectedResult=true
        val ActualResult= response.success
        Assert.assertEquals(ExpectedResult, ActualResult)
    }


}