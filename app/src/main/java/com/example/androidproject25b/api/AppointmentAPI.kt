package com.example.androidproject25b.api

import com.example.androidproject25b.Entity.Appointment
import com.example.androidproject25b.response.AddAppointmentResponse
import com.example.androidproject25b.response.AppointmentResponse
import com.example.androidproject25b.response.DeleteAppointmentResponse
import retrofit2.Response
import retrofit2.http.*
import java.util.*

interface AppointmentAPI {
    @POST("appointment/insert6/")
    suspend fun insertAppointment(
        @Header("Authorization") token:String,
    @Body appoinment:Appointment
    ):Response<AddAppointmentResponse>

    @GET("Appointment/all")
    suspend fun getAllAppointment(
        @Header ("Authorization") token: String
    ):Response<AppointmentResponse>

    @GET("Appointment/delete/{id}")
    suspend fun deleteAppointment(
        @Header ("Authorization") token: String,
        @Path("id") id:String
    ):Response<DeleteAppointmentResponse>

}