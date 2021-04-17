package com.example.androidproject25b

import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.widget.ImageViewCompat
import com.example.androidproject25b.Fragments.AboutActivity
import com.example.androidproject25b.Fragments.AppointmentActivity
import com.example.androidproject25b.Fragments.DashboardActivity
import com.example.androidproject25b.Fragments.ReviewActivity
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    private lateinit var dashboard: ImageView
    private lateinit var appointment: ImageView
    private lateinit var review: ImageView
    private lateinit var about: ImageView


    private lateinit var googlemap :AppCompatImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        dashboard =findViewById(R.id.btndashboard)
        appointment=findViewById(R.id.btnappointment)
        review=findViewById(R.id.btnreview)
        about =findViewById(R.id.btnabout)

        dashboard.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.linearcontainer,DashboardActivity())
                addToBackStack(null)
                commit()
            }
        }
        appointment.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.linearcontainer,AppointmentActivity())
                addToBackStack(null)
                commit()
            }
        }
        review.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.linearcontainer,ReviewActivity())
                addToBackStack(null)
                commit()
            }
        }

        about.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.linearcontainer,AboutActivity())
                addToBackStack(null)
                commit()
            }
        }



//        tvProximitySensor = findViewById(R.id.tvProximitySensor)
//        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
//
//        if (!checkSensor())
//            return
//        else {
//            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
//            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
//        }
//
//        googlemap =findViewById(R.id.btnGoogleMap)
//
//        googlemap.setOnClickListener {
//            startActivity(Intent(this@DashboardActivity,MapsActivity::class.java))
//        }
//    }

//    private fun checkSensor(): Boolean {
//        var flag = true
//        if (sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) == null) {
//            flag = false
//        }
//        return flag
//    }
//
//    override fun onSensorChanged(event: SensorEvent?) {
//        val values = event!!.values[0]
//
//        if(values<=4)
//            tvProximitySensor.text = "Object is near"
//        else
//            tvProximitySensor.text = "Object is far"
//    }
//
//    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
//
    }
}