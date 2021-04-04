package com.example.androidproject25b.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Appointment (
        @PrimaryKey
        val _id:String= "",
    val device_name:String?= null,
    val device_model:String?= null,
    val appointment_date:String?= null,
    val location:String?= null,
        val issue:String?= null


    )
