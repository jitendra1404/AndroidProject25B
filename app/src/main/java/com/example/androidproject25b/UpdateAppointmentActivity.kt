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
import com.example.androidproject25b.Entity.Appointment
import com.example.androidproject25b.db.AppointmentDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

        val intent = intent.getParcelableExtra<Appointment>("Donor")
        if (intent != null) {
            DeviceName.setText(intent.device_name)
            DeviceModel.setText(intent.device_model)
            AppointmentDate.setText(intent.appointment_date)
            Location.setText(intent.location)
            Issue.setText(intent.issue)
        }

        Edit.setOnClickListener {
            val appointment = Appointment(
                device_name = DeviceName.text.toString(),
                device_model = DeviceName.text.toString(),
                appointment_date = AppointmentDate.text.toString(),
                location = Location.text.toString(),
                issue = Issue.text.toString()
            )
            appointment.id = intent!!.id

            CoroutineScope(Dispatchers.IO).launch {
                AppointmentDB.getInstance(this@UpdateAppointmentActivity).getAppointmentDAO()
                    .updateAppointment(appointment)
                startActivity(Intent(this@UpdateAppointmentActivity, TabActivity::class.java))
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