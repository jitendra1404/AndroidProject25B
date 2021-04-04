package com.example.androidproject25b.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject25b.Entity.Appointment
import com.example.androidproject25b.R
import com.example.androidproject25b.Repository.AppointmentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class AppointmentAdapter(

    private val context :Context,
    private val lstAppointments:MutableList<Appointment>
) : RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>(){
    class AppointmentViewHolder(view:View) :RecyclerView.ViewHolder(view){
        val device_name:TextView = view.findViewById(R.id.tvdevice_name)
        val device_model:TextView=view.findViewById(R.id.tvdevice_model)
        val appointment_date:TextView=view.findViewById(R.id.tvappointment_date)
        val location:TextView=view.findViewById(R.id.tvlocation)
        val issue:TextView=view.findViewById(R.id.tvissue)
        val btndelete:ImageButton =view.findViewById(R.id.btnDelete)
        val btnupdate:ImageButton =view.findViewById(R.id.btnUpdate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
       val view = LayoutInflater.from(parent.context)
           .inflate(R.layout.custom_appointment_layout,parent, false)
        return AppointmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        val appointment =lstAppointments[position]
        holder.device_name.text =appointment.device_name
        holder.device_model.text =appointment.device_model
        holder.appointment_date.text =appointment.appointment_date.toString()
        holder.location.text =appointment.location
        holder.issue.text = appointment.issue
    }

    override fun getItemCount(): Int {
        return lstAppointments.size
    }


}