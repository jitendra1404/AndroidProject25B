package com.example.androidproject25b.db

import androidx.room.Database
import com.example.androidproject25b.Entity.Customer
import com.example.androidproject25b.Entity.User

@Database(
    entities = [ (User::class),(Customer::class)],
    version = 1,
    exportScheme =false
)


abstract class UserDB :()  {
}