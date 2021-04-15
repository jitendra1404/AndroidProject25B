package com.example.androidproject25b.response

import com.example.androidproject25b.Entity.Appointment

data class GetAllAppointmentResponse (

    val success:Boolean?=null,
    val count:Int?=null,
    val data :MutableList<Appointment>?=null

        )
