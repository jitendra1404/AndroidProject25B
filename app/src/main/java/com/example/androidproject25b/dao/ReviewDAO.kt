package com.example.androidproject25b.dao

import androidx.room.*

import com.example.androidproject25b.Entity.Review

@Dao
interface ReviewDAO {

    @Insert
    suspend fun insertReview(review: Review)

    @Query("SELECT * FROM Review")
    suspend fun getAllReview():MutableList<Review>

    @Delete
    suspend fun deleteReview(review: Review)

}