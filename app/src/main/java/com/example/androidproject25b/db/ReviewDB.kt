package com.example.androidproject25b.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidproject25b.Entity.Appointment
import com.example.androidproject25b.Entity.Review
import com.example.androidproject25b.dao.AppointmentDAO
import com.example.androidproject25b.dao.ReviewDAO

@Database(
    entities = [(Review::class)],
    version = 3,
    exportSchema = false
)

abstract class ReviewDB: RoomDatabase(){
    abstract fun getReviewDAO(): ReviewDAO

    companion object{
        @Volatile
        private var instance : ReviewDB? = null
        fun getInstance(context: Context): ReviewDB{
            if (instance==null){
                synchronized(ReviewDB::class){
                    instance = buildDatabase(context)
                }
            }
            return instance!!
        }
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ReviewDB::class.java,
                "DonorDB"
            )
                .fallbackToDestructiveMigration()
                .build()
    }

}
