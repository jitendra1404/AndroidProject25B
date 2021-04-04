package com.example.androidproject25b.response

import com.example.androidproject25b.Entity.Appointment

data class AppointmentResponse(
    val success:Boolean?=null,
    val data:MutableList<Appointment>? =null

)
