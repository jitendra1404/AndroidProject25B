package com.example.androidproject25b

import com.example.androidproject25b.Entity.Appointment
import com.example.androidproject25b.Entity.Review
import com.example.androidproject25b.Entity.User
import com.example.androidproject25b.Repository.AppointmentRepository
import com.example.androidproject25b.Repository.ReviewRepository
import com.example.androidproject25b.Repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class UserMaanagmentTest {

    private  var userRepository: UserRepository?=null
    private var appointmentRepository: AppointmentRepository?=null
    private  var reviewRepository: ReviewRepository?=null

    //...........................USER login and Register Testing...........................//

    @Test

    fun checkLogin() = runBlocking {
        userRepository = UserRepository()
        val response= userRepository!!.loginUser("kishan", "kishan")
        val ExpectedResult=true
        val ActualResult=response.success
        Assert.assertEquals(ExpectedResult, ActualResult)
    }

    @Test
    fun checkRegister() = runBlocking {

       val user = User(custo_name = "kiran", custo_password = "kiran",
           custo_email ="kiran123@gmail.com", custo_mobile = "9800728252", custo_address = "patan")

        userRepository = UserRepository()
        val response = userRepository!!.registerUser(user)
        val ExpectedResult=false
        val ActualResult= response.success
        Assert.assertEquals(ExpectedResult, ActualResult)
    }

    //...........................Review Add Testing...........................//

    @Test
    fun checkAddReview() = runBlocking {

        val response= reviewRepository?.addReview(Review(_id = String(), feedback_title = "battery issue service",
            feedback_description = "technician are porvides really nice service and repair", customer_name = "Ranjan Rimal"))
        reviewRepository = ReviewRepository()
        val ExpectedResult=true
        val ActualResult= response?.success
        Assert.assertEquals(ExpectedResult, ActualResult)
    }

    //...........................Appointment Add & Update Testing...........................//

    @Test
    fun checkUpdateAppointment() = runBlocking {

//        val appointment = Appointment(device_name = "dell laptop", device_model= "dell 55956 A7", appointment_date = "2020-02-04",
//            location = "New road, Ktm", issue = "keyword not working")

        appointmentRepository = AppointmentRepository()
        val response = appointmentRepository!!.putAppointment("6078ecc59784473b806e9289",Appointment(_id = String(),device_name = "Dell",
            device_model = "Dell 5559",appointment_date ="10-10-2005",location = "Ktm", issue = "Display not working" ))
        val ExpectedResult=true
        val ActualResult= response.success
        Assert.assertEquals(ExpectedResult, ActualResult)
    }

    @Test
    fun checkAddAppointment() = runBlocking {

        val response= appointmentRepository?.addAppointment(Appointment(_id = String(), device_name = "Lenevo",
            device_model ="Lenevo X240",appointment_date = "2021-04-16",location = "kathamdu, lazimpat, street no -109",
            issue = "Screen will blank suddely during running"))

        appointmentRepository = AppointmentRepository()
        val ExpectedResult=true
        val ActualResult= response?.success
        Assert.assertEquals(ExpectedResult, ActualResult)
    }
}