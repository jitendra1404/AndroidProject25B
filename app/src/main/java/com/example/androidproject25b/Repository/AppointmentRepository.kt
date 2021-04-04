package com.example.androidproject25b.Repository

import android.content.Context
import android.icu.util.Calendar.getInstance
import com.example.androidproject25b.Entity.Appointment
import com.example.androidproject25b.api.AppointmentAPI
import com.example.androidproject25b.api.MyAPIRequest
import com.example.androidproject25b.api.ServiceBuilder
import com.example.androidproject25b.db.AppointmentDB
import com.example.androidproject25b.response.AddAppointmentResponse
import com.example.androidproject25b.response.AppointmentResponse
import com.example.androidproject25b.response.DeleteAppointmentResponse
import java.util.Calendar.getInstance


class AppointmentRepository: MyAPIRequest(){
    private val appointmentAPI =
        ServiceBuilder.buildServices(AppointmentAPI::class.java)

    suspend fun insertAppointment(appointment: Appointment):AddAppointmentResponse{
        return apiRequest {
            appointmentAPI.insertAppointment(ServiceBuilder.token!!, appointment)
        }
    }

    suspend fun getAllAppointment():AppointmentResponse{
        return apiRequest {
            appointmentAPI.getAllAppointment(ServiceBuilder.token!!)
        }
    }
//    suspend fun insertBulkAppointment(context : Context, appointment : List<Appointment>){
//        // Delete all students
//        AppointmentDB.getInstance(context).getAppointmentDAO().DeleteAllAppointments()
//        // Insert all data in database
//        AppointmentDB.getInstance(context).getStudentDAO().insertBulkAppointment(appointment )
//    }
//
//    // get data from repository
//    suspend fun getAllAppointmentsFromRoom(context : Context) : MutableList<Appointment>{
//        return AppointmentDB.getInstance(context).getStudentDAO().getAllAppointments()
//    }
        suspend fun deleteAppointment(id:String):DeleteAppointmentResponse {
            return apiRequest {
                appointmentAPI.deleteAppointment(ServiceBuilder.token!!, id)
            }
        }


}
