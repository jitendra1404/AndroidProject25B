package com.example.androidproject25b.Repository

import com.example.androidproject25b.Entity.Appointment
import com.example.androidproject25b.api.AppointmentAPI
import com.example.androidproject25b.api.MyAPIRequest
import com.example.androidproject25b.api.ServiceBuilder
import com.example.androidproject25b.response.AddAppointmentResponse
import com.example.androidproject25b.response.DeleteAppointmentResponse
import com.example.androidproject25b.response.GetAllAppointmentResponse
import okhttp3.MultipartBody

class AppointmentRepository :MyAPIRequest() {
    private val AppointmentAPI =
        ServiceBuilder.buildServices(AppointmentAPI::class.java)

    //Add Appointment
    suspend fun addAppointment(appointment: Appointment): AddAppointmentResponse {
        return apiRequest {
            AppointmentAPI.addAppointment(
                ServiceBuilder.token!!, appointment
            )
        }
    }

    //Delete Appointment

    suspend fun deleteAppointment(id: String): DeleteAppointmentResponse {
        return apiRequest {
            AppointmentAPI.deleteAppointment(
                ServiceBuilder.token!!, id
            )
        }
    }

    // Get Appointment

    suspend fun getAllAppointment(): GetAllAppointmentResponse {
        return apiRequest {
            AppointmentAPI.getAllAppointment(
                ServiceBuilder.token!!
            )
        }
    }

//    //upload image
//    suspend fun uploadImage(id: String,body: MultipartBody.Part)
//            : ImageResponse {
//        return apiRequest {
//            DonorAPI.uploadImage(ServiceBuilder.token!!,id,body)
//        }
//    }
//}
}