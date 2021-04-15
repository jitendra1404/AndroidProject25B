package com.example.androidproject25b.Adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject25b.AddAppointmentActivity
import com.example.androidproject25b.Entity.Appointment
import com.example.androidproject25b.R
import com.example.androidproject25b.Repository.AppointmentRepository
import com.example.androidproject25b.db.AppointmentDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class AppointmentAdapter (

    private val context: Context,
    private val lstAppointment:MutableList<Appointment>
    ): RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>() {

    class AppointmentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //        val profile: ImageView
        val devicename: TextView
        val devicemodel: TextView
        val appointmentdate: TextView
        val location: TextView
        val issue: TextView
        val update: ImageButton
        val delete: ImageButton

        init {
//            profile=view.findViewById(R.id.profile)
            devicename = view.findViewById(R.id.tvdevicename)
            devicemodel = view.findViewById(R.id.tvdevicemodel)
            appointmentdate = view.findViewById(R.id.tvappointmentdate)
            location = view.findViewById(R.id.tvlocation)
            issue = view.findViewById(R.id.tvissue)
            update = view.findViewById(R.id.btnupdate)
            delete = view.findViewById(R.id.btndelete)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_appointment_layout, parent, false)
        return AppointmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        val appointment = lstAppointment[position]
        holder.devicename.text = appointment.devicename
        holder.devicemodel.text = appointment.devicemodel
        holder.appointmentdate.text = appointment.appointmentdate
        holder.location.text = appointment.location
        holder.issue.text = appointment.issue

        holder.update.setOnClickListener {
            val intent = Intent(context, AddAppointmentActivity::class.java)
            intent.putExtra("Donor", appointment)
            context.startActivity(intent)
        }

        holder.delete.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete ${appointment.devicename}")
            builder.setMessage("Are you sure do you want to delete ${appointment.devicename} ?")
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setPositiveButton("Yes") { _, _ ->
                deleteAppointment(appointment)
            }
            builder.setNegativeButton("No") { _, _ ->
                Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show()
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
    }

    private fun deleteAppointment(appointment: Appointment) {
        CoroutineScope(Dispatchers.IO).launch {
            AppointmentDB.getInstance(context).getAppointmentDAO().deleteAppointment(appointment)
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    context,
                    "${appointment.devicename} deleted successfully",
                    Toast.LENGTH_SHORT
                ).show()
            }
            try {
                val appointmentRepository = AppointmentRepository()
                val response = appointmentRepository.deleteAppointment(appointment._id!!)
                if (response.success == true) {
                    withContext(Dispatchers.Main) {
//                        Toast.makeText(context, "${donor.fullname} deleted successfully", Toast.LENGTH_SHORT)
//                            .show()
//                    }
//                }
//                        withContext(Dispatchers.Main) {
                        lstAppointment.remove(appointment)
                        notifyDataSetChanged()
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        "Error ${ex.localizedMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return lstAppointment.size
    }

}



