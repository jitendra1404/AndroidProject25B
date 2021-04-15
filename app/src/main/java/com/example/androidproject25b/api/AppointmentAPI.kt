package com.example.androidproject25b.api

import com.example.androidproject25b.Entity.Appointment
import com.example.androidproject25b.response.AddAppointmentResponse
import com.example.androidproject25b.response.DeleteAppointmentResponse
import com.example.androidproject25b.response.GetAllAppointmentResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface AppointmentAPI {

    //Add Appointment
    @POST("bgroup/insert")
    suspend fun addAppointment(
        @Header("Authorization") token:String,
        @Body appointment: Appointment
    ): Response<AddAppointmentResponse>

    //Delete Appointment
    @DELETE("bgroup/delete/{id}")
    suspend fun deleteAppointment(
        @Header("Authorization") token: String,
        @Path("id")id:String
    ): Response<DeleteAppointmentResponse>

    //GET all Appointment
    @GET("bgroup/all")
    suspend fun getAllAppointment(
        @Header("Authorization") token : String,
    ): Response<GetAllAppointmentResponse>

//    //upload image
//    @Multipart
//    @PUT("donor/{id}/photo")
//    suspend fun uploadImage(
//        @Header("Authorization") token: String,
//        @Path("id") id: String,
//        @Part file: MultipartBody.Part
//    ): Response<ImageResponse>
}