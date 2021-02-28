package com.example.androidproject25b.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.androidproject25b.Entity.User


//@Dao
//interface UserDAO {
//
//    @Insert
//
//    suspend fun resgisterUser(user: User)
//
//    @Query("select * from User where custo_name=(:username) and custo_password=(:password)")
//    suspend fun checkUser(username:String, password :String):User
//
//}