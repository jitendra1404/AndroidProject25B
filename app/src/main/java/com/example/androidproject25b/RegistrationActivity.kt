package com.example.androidproject25b

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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

class RegistrationActivity : AppCompatActivity()
//    ,SensorEventListener
{

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var address: EditText
    private lateinit var mobile: EditText
    private lateinit var email: EditText
    private lateinit var confirmpassword: EditText
    private lateinit var signUp: Button

//    private  var sensorManager: SensorManager?=null
//    private  var sensor: Sensor?=null
//    private var mAccel= 0f
//    private var mAccelCurrent= 0f
//    private var mAccelLast= 0f


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        username = findViewById(R.id.edRegisterUsername)
        email = findViewById(R.id.edEmail)
        mobile = findViewById(R.id.edmobile)
        address = findViewById(R.id.edAddress)
        password = findViewById(R.id.edPassword)
        confirmpassword = findViewById(R.id.edConfirmPassword)
        signUp = findViewById(R.id.btnSingUP)

//        sensorManager=getSystemService(Context.SENSOR_SERVICE)as SensorManager
//        sensor=sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
//        mAccel= 10f;
//        mAccelCurrent=SensorManager.GRAVITY_EARTH;
//        mAccelLast=SensorManager.GRAVITY_EARTH


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

//    override fun onResume() {
//        super.onResume()
//        sensorManager!!.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
//    }
//    override fun onPause() {
//        super.onPause()
//        sensorManager!!.unregisterListener(this)
//    }
//
//    override fun onSensorChanged(event: SensorEvent?) {
//        val x: Float = event!!.values[0]
//        val y: Float = event!!.values[1]
//        val z: Float = event!!.values[2]
//        mAccelLast = mAccelCurrent
//        mAccelCurrent = Math.sqrt((x * x + y * y + z * z).toDouble()).toFloat()
//        val delta: Float = mAccelCurrent - mAccelLast
//        mAccel = mAccel * 0.9f + delta
//        if (mAccel > 12) {
//            leftactivity()
//        }
//    }
//
//    private fun leftactivity() {
//        val intent = Intent(this@RegistrationActivity, LoginActivity::class.java)
//        startActivity(intent)
//    }
//
//    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
//
//    }
}



