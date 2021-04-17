package com.example.androidproject25b.dao

import androidx.room.*
import com.example.androidproject25b.Entity.Appointment
import com.example.androidproject25b.Entity.User

@Dao
interface AppointmentDAO {

    @Insert
    suspend fun insertAppointment(appointment: Appointment)

   @Query("SELECT * FROM Appointment")
   suspend fun getAllAppointment():MutableList<Appointment>

   @Update
   suspend fun updateAppointment(appointment: Appointment)

   @Delete
   suspend fun deleteAppointment(appointment: Appointment)
//
}