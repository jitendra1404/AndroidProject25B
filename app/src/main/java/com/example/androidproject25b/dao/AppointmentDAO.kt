//package com.example.androidproject25b.dao
//
//import androidx.room.*
//import com.example.androidproject25b.Entity.Appointment
//import com.example.androidproject25b.Entity.User
//
//@Dao
//interface AppointmentDAO {
//    @Insert
//    suspend fun insertAppointment(appointment: Appointment)
//
//   @Insert(onConflict = OnConflictStrategy.REPLACE)
//   suspend fun insertBulkAppointment(appointment: List<Appointment>)
//
//   @Query("SELECT * FROM Appointment")
//   suspend fun getAllAppointments():MutableList<Appointment>
//
//   @Update
//   suspend fun updateAppointment(appointment: Appointment)
//
//   @Delete
//   suspend fun DeleteAppointment(appointment: Appointment)
//
//   @Query("DELETE from appointment")
//   suspend fun DeleteAllAppointment()
//
//}