package com.example.androidproject25b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.androidproject25b.Entity.Appointment
import com.example.androidproject25b.db.AppointmentDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateAppointmentActivity : AppCompatActivity() {

    private lateinit var DeviceName: EditText
    private lateinit var DeviceModel: EditText
    private lateinit var AppointmentDate: EditText
    private lateinit var Location: EditText
    private lateinit var Issue: EditText
    private lateinit var Edit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_appointment)

        DeviceName=findViewById(R.id.etDeviceName)
        DeviceModel=findViewById(R.id.etDeviceModel)
        AppointmentDate=findViewById(R.id.etAppointmentDate)
        Location=findViewById(R.id.etLocation)
        Issue=findViewById(R.id.etIssue)
        Edit=findViewById(R.id.btnEdit)

        val intent = intent.getParcelableExtra<Appointment>("Donor")
        if (intent !=null){
            DeviceName.setText(intent.devicename)
            DeviceModel.setText(intent.devicemodel)
            AppointmentDate.setText(intent.appointmentdate)
            Location.setText(intent.location)
            Issue.setText(intent.issue)
        }

        Edit.setOnClickListener {
            val appointment = Appointment(devicename = DeviceName.text.toString(),devicemodel = DeviceName.text.toString(),
                appointmentdate = AppointmentDate.text.toString(),location = Location.text.toString(),issue = Issue.text.toString())
            appointment.id=intent!!.id

            CoroutineScope(Dispatchers.IO).launch {
                AppointmentDB.getInstance(this@UpdateAppointmentActivity).getAppointmentDAO().updateAppointment(appointment)
                startActivity(Intent(this@UpdateAppointmentActivity,TabActivity::class.java))
            }

        }


    }
}