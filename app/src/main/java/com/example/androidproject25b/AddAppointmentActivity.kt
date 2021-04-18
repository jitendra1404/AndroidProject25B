package com.example.androidproject25b

import android.content.Context
import android.graphics.Color
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.lang.Exception

class AddAppointmentActivity : AppCompatActivity()
//    ,SensorEventListener
{

//    private lateinit var sensorManager:SensorManager
//    private lateinit var LightSensor:Sensor

    private lateinit var etdevicename: EditText
    private lateinit var etdevicemodel: EditText
    private lateinit var etappointmentdate: EditText
    private lateinit var etlocation: EditText
    private lateinit var etissue: EditText
    private lateinit var btnsubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_appointment)

        etdevicename=findViewById(R.id.etdevicename)
        etdevicemodel=findViewById(R.id.etdevicemodel)
        etappointmentdate=findViewById(R.id.etappointmentdate)
        etlocation=findViewById(R.id.etlocation)
        etissue=findViewById(R.id.etissue)
        btnsubmit=findViewById(R.id.btnsubmit)

//        sensorManager=getSystemService(Context.SENSOR_SERVICE)as SensorManager
//        LightSensor=sensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)

        btnsubmit.setOnClickListener {
            addAppointment()
        }

    }

    private fun addAppointment() {
        val devicename = etdevicename.text.toString()
        val devicemodel = etdevicemodel.text.toString()
        val appointmentdate = etappointmentdate.text.toString()
        val location = etlocation.text.toString()
        val issue= etissue.text.toString()

        val appointment = Appointment(
            device_name = devicename,
            device_model = devicemodel,
            appointment_date = appointmentdate,
            location = location,
            issue= issue
        )
        CoroutineScope(Dispatchers.IO).launch {
            try {

                AppointmentDB
                    .getInstance(this@AddAppointmentActivity)
                    .getAppointmentDAO()
                    .insertAppointment(appointment)

                val appointmentRepository = AppointmentRepository()
                val response = appointmentRepository.addAppointment(appointment)
                if (response.success == true) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@AddAppointmentActivity,
                            "New Appointment Added Successfully",
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

//    override fun onResume() {
//        super.onResume()
//        sensorManager!!.registerListener(this,LightSensor,SensorManager.SENSOR_DELAY_NORMAL)
//    }
//    override fun onPause() {
//        super.onPause()
//        sensorManager!!.unregisterListener(this)
//    }
//
//    var isRunning=false
//
//    override fun onSensorChanged(event: SensorEvent?) {
//        try {
//            if (event!!.values[0]<20 && !isRunning) {
//                isRunning=true
//                window.decorView.setBackgroundColor(Color. GRAY)
//            }else{isRunning = false
//                window.decorView.setBackgroundColor(Color.YELLOW)
//            }
//        }catch (e: IOException){}
//    }
//
//    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
//
//    }

}

