package com.example.androidproject25b.response

import com.example.androidproject25b.Entity.Appointment

data class UpdateAppointment (
    val success:Boolean?=null,
    val data: Appointment?=null
)
