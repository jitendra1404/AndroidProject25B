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
        val response=userRepository.loginUser("ashish", "ashish")
        val ExpectedResult=true
        val ActualResult=response.success
        Assert.assertEquals(ExpectedResult, ActualResult)
    }

    @Test

    fun checkRegister() = runBlocking {

       val user = User(custo_name = "ashish1234", custo_password = "ashish", custo_email ="ashish1@gmail.com", custo_mobile = "9807278869", custo_address = "chitwan")

        userRepository = UserRepository()
        val response =userRepository.registerUser(user)
        val ExpectedResult=true
        val ActualResult= response.success
        Assert.assertEquals(ExpectedResult, ActualResult)
    }


}