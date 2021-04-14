package com.example.androidproject25b

import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.widget.ImageViewCompat
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity(),SensorEventListener {

    private lateinit var tvProximitySensor: TextView
    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null

    private lateinit var googlemap :AppCompatImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        tvProximitySensor = findViewById(R.id.tvProximitySensor)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        if (!checkSensor())
            return
        else {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }

        googlemap =findViewById(R.id.btnGoogleMap)

        googlemap.setOnClickListener {
            startActivity(Intent(this@DashboardActivity,MapsActivity::class.java))
        }
    }

    private fun checkSensor(): Boolean {
        var flag = true
        if (sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) == null) {
            flag = false
        }
        return flag
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val values = event!!.values[0]

        if(values<=4)
            tvProximitySensor.text = "Object is near"
        else
            tvProximitySensor.text = "Object is far"
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}