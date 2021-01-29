package com.example.androidproject25b.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.androidproject25b.Entity.Customer
import com.example.androidproject25b.Entity.User
import com.example.androidproject25b.dao.UserDAO

@Database(
    entities = [(User::class),(Customer::class)],
    version = 2,
    exportSchema = false
)

abstract class UserDB : RoomDatabase() {
    abstract fun getUserDAO(): UserDAO


    companion object {
        @Volatile
        private var instance: UserDB? = null

        fun getInstance(context: Context): UserDB {
            if (instance == null)
                synchronized(UserDB::class) {
                    instance =buildDatabase(context)
                }
             return instance!!
        }
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                UserDB::class.java,
                "UserDB"
            ).build()
    }

    }
