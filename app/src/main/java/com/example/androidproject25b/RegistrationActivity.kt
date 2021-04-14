package com.example.androidproject25b

import android.content.Intent
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.androidproject25b.Entity.NotificationChannel
import com.example.androidproject25b.Entity.User
import com.example.androidproject25b.Repository.UserRepository
import com.example.androidproject25b.api.ServiceBuilder
//import com.example.androidproject25b.db.UserDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class RegistrationActivity : AppCompatActivity(),SensorEventListener {


    private lateinit var tvAccelerometerSensor: Button
    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var address: EditText
    private lateinit var mobile: EditText
    private lateinit var email: EditText
    private lateinit var confirmpassword: EditText
    private lateinit var signUp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        tvAccelerometerSensor = findViewById(R.id.tvAccelerometerSensor)

        setUpSensorStuff()
    }

    private fun setUpSensorStuff() {
        // Create the sensor manager
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        // Specify the sensor you want to listen to
        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.also { accelerometer ->
            sensorManager.registerListener(
                this,
                accelerometer,
                SensorManager.SENSOR_DELAY_FASTEST,
                SensorManager.SENSOR_DELAY_FASTEST
            )
        }

        username = findViewById(R.id.edRegisterUsername)
        email = findViewById(R.id.edEmail)
        mobile = findViewById(R.id.edmobile)
        address = findViewById(R.id.edAddress)
        password = findViewById(R.id.edPassword)
        confirmpassword = findViewById(R.id.edConfirmPassword)
        signUp = findViewById(R.id.btnSingUP)

        signUp.setOnClickListener {

            lowPriorityNotification()

            val username = username.text.toString()
            val address = address.text.toString()
            val mobile = mobile.text.toString()
            val email = email.text.toString()
            val Password = password.text.toString()
            val ConfirmPassword = confirmpassword.text.toString()

            if (Password != ConfirmPassword) {
                password.error = "password does not match"
                password.requestFocus()
                return@setOnClickListener

            } else {
                val user = User(
                    custo_name = username,
                    custo_address = address,
                    custo_mobile = mobile,
                    custo_email = email,
                    custo_password = Password
                )
//                Toast.makeText(this, "AAYO", Toast.LENGTH_SHORT).show()
                CoroutineScope(Dispatchers.IO).launch {

                    try {
                        val repository = UserRepository()
                        val response = repository.registerUser(user)
                        if (response.success == true) {
                            ServiceBuilder.token = "Bearer " + response.token
                            withContext(Main) {
                                Toast.makeText(
                                    this@RegistrationActivity,
                                    "Successfully registered",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    } catch (ex: Exception) {
                        withContext(Main) {
                            Toast.makeText(
                                this@RegistrationActivity,
                                ex.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
//                    }
//
//                    UserDB.getInstance(this@RegistrationActivity).getUserDAO().resgisterUser(user)
//                }
//                Toast.makeText(this, "User sinUp", Toast.LENGTH_SHORT).show()
//                    }


                    }
                }
            }
        }
    }

    private fun lowPriorityNotification() {

            val notificationManager = NotificationManagerCompat.from(this)

            val notificationChannels = NotificationChannel(this)
            notificationChannels.createNotificationChannels()

            val notification = NotificationCompat.Builder(this,notificationChannels.CHANNEL_2)
                .setSmallIcon(R.drawable.ic_baseline_message_24)
                .setContentTitle("Low priority notification")
                .setContentText("User Successfully Register")
                .setColor(Color.BLUE)
                .build()

            notificationManager.notify(2, notification)

        }

    override fun onSensorChanged(event: SensorEvent?) {
        // Checks for the sensor we have registered
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            //Log.d("Main", "onSensorChanged: sides ${event.values[0]} front/back ${event.values[1]} ")

            // Sides = Tilting phone left(10) and right(-10)
            val sides = event.values[0]

            // Up/Down = Tilting phone up(10), flat (0), upside-down(-10)
            val upDown = event.values[1]

            tvAccelerometerSensor.apply {
                rotationX = upDown * 3f
                rotationY = sides * 3f
                rotation = -sides
                translationX = sides * -10
                translationY = upDown * 10
            }

            // Changes the colour of the square if it's completely flat
            val color = if (upDown.toInt() == 0 && sides.toInt() == 0) Color.GREEN else Color.RED
            tvAccelerometerSensor.setBackgroundColor(color)

            tvAccelerometerSensor.text = "up/down ${upDown.toInt()}\nleft/right ${sides.toInt()}"
        }
    }
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    override fun onDestroy() {
        sensorManager.unregisterListener(this)
        super.onDestroy()
    }

}



