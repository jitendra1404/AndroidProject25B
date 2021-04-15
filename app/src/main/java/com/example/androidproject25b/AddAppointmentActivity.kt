package com.example.androidproject25b

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.androidproject25b.Entity.Appointment
import com.example.androidproject25b.Repository.AppointmentRepository
import com.example.androidproject25b.db.AppointmentDB
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class AddAppointmentActivity : AppCompatActivity(), SensorEventListener {


    private lateinit var etdevicename: EditText
    private lateinit var etdevicemodel: EditText
    private lateinit var etappointmentdate: EditText
    private lateinit var etlocation: EditText
    private lateinit var etissue: EditText
    private lateinit var btnsubmit: Button


    private lateinit var tvGyroscopeSensor: TextView
    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_appointment)

        etdevicename=findViewById(R.id.etdevicename)
        etdevicemodel=findViewById(R.id.etdevicemodel)
        etappointmentdate=findViewById(R.id.etappointmentdate)
        etlocation=findViewById(R.id.etlocation)
        etissue=findViewById(R.id.etissue)
        btnsubmit=findViewById(R.id.btnsubmit)

        btnsubmit.setOnClickListener {
            addAppointment()
        }

        // this is appointment activity//

        tvGyroscopeSensor = findViewById(R.id.tvGyroscopeSensor)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        if (!checkSensor())
            return
        else {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    private fun addAppointment() {
        val devicename = etdevicename.text.toString()
        val devicemodel = etdevicemodel.text.toString()
        val appointmentdate = etappointmentdate.text.toString()
        val location = etlocation.text.toString()
        val issue=etissue.text.toString()

        val appointment = Appointment(
            devicename = devicename,
            devicemodel = devicemodel,
            appointmentdate = appointmentdate,
            location = location,
            issue= issue
        )
        CoroutineScope(Dispatchers.IO).launch {
            try {

                AppointmentDB
                    .getInstance(this@AddAppointmentActivity)
                    .getAppointmentDAO()
                    .insertAppointment(appointment)

                val donorRepository = AppointmentRepository()
                val response = donorRepository.addAppointment(appointment)
                if (response.success == true) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@AddAppointmentActivity,
                            "Blood Information Added Successfully",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@AddAppointmentActivity,
                        "Error ${ex.localizedMessage}",
                        Toast.LENGTH_SHORT
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
