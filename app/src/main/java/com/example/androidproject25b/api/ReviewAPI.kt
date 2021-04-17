package com.example.androidproject25b.api

import com.example.androidproject25b.Entity.Appointment
import com.example.androidproject25b.Entity.Review
import com.example.androidproject25b.response.*
import retrofit2.Response
import retrofit2.http.*

interface ReviewAPI {

    //Add Review
    @POST("Review/insert66")
    suspend fun addReview(
        @Header("Authorization") token:String,
        @Body review:Review
    ): Response<AddReviewResponse>

    //Delete Review
    @DELETE("Review/delete/{id}")
    suspend fun deleteReview(
        @Header("Authorization") token: String,
        @Path("id")id:String
    ): Response<DeleteReviewResponse>

    //GET all Review
    @GET("Review/All")
    suspend fun getAllReview(
        @Header("Authorization") token : String,
    ): Response<GetAllReviewResponse>
}