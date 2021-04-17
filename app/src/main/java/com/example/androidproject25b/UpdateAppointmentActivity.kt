package com.example.androidproject25b

import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.androidproject25b.Entity.Appointment
import com.example.androidproject25b.Repository.AppointmentRepository
import com.example.androidproject25b.db.AppointmentDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpdateAppointmentActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var DeviceName: EditText
    private lateinit var DeviceModel: EditText
    private lateinit var AppointmentDate: EditText
    private lateinit var Location: EditText
    private lateinit var Issue: EditText
    private lateinit var Edit: Button

    private lateinit var tvGyroscopeSensor: TextView
    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_appointment)

        tvGyroscopeSensor = findViewById(R.id.tvGyroscopeSensor)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        if (!checkSensor())
            return
        else {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }

        DeviceName = findViewById(R.id.etDeviceName)
        DeviceModel = findViewById(R.id.etDeviceModel)
        AppointmentDate = findViewById(R.id.etAppointmentDate)
        Location = findViewById(R.id.etLocation)
        Issue = findViewById(R.id.etIssue)
        Edit = findViewById(R.id.btnEdit)

        val appointment = intent.getParcelableExtra<Appointment>("Appointment")
        if (appointment != null) {
            DeviceName.setText(appointment.device_name)
            DeviceModel.setText(appointment.device_model)
            AppointmentDate.setText(appointment.appointment_date)
            Location.setText(appointment.location)
            Issue.setText(appointment.issue)
        }

        Edit.setOnClickListener {
            updateAppointment()

        }
    }

    private fun updateAppointment() {
        val intent = intent.getParcelableExtra<Appointment>("Appointment")
        val devicename = DeviceName.text.toString()
        val devicemodel = DeviceModel.text.toString()
        val appointmentdate = AppointmentDate.text.toString()
        val location= Location.text.toString()
        val issue = Issue.text.toString()
        val appointment = Appointment(
            device_name = devicename,
            device_model = devicemodel,
            appointment_date = appointmentdate,
            location = location,
            issue = issue
        )
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val appointmentRepository = AppointmentRepository()
                val response = appointmentRepository.putAppointment(intent?._id!!, appointment)
                if (response.success ==true) {
                    //for update image
//                    if (imageUrl != null) {
//                        updatefoodimage(intent?._id!!)
//                    }
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@UpdateAppointmentActivity,
                            " your Foods has been updated", Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@UpdateAppointmentActivity,
                        ex.localizedMessage, Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun checkSensor(): Boolean {
        var flag = true
        if (sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) == null) {
            flag = false
        }
        return flag

    }

    override fun onSensorChanged(event: SensorEvent?) {
        val values = event!!.values[1]
        if (values < 0)
            tvGyroscopeSensor.text = "Left"
        else if (values > 0)
            tvGyroscopeSensor.text = "Right"
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}