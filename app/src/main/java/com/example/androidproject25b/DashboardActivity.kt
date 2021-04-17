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
import com.example.androidproject25b.Fragments.*
import com.example.androidproject25b.Fragments.DashboardActivity
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    private lateinit var dashboard: Button
    private lateinit var appointment: Button
    private lateinit var review: Button
    private lateinit var about: Button
//    private lateinit var product:Button


    private lateinit var googlemap: AppCompatImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        dashboard = findViewById(R.id.btndashboard)
        appointment = findViewById(R.id.btnappointment)
        review = findViewById(R.id.btnreview)
        about = findViewById(R.id.btnabout)
//        product=findViewById(R.id.btnproduct)

        dashboard.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.linearcontainer, DashboardActivity())
                addToBackStack(null)
                commit()
            }
        }
        appointment.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.linearcontainer, AppointmentActivity())
                addToBackStack(null)
                commit()
            }
        }
        review.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.linearcontainer, ReviewActivity())
                addToBackStack(null)
                commit()
            }
        }

        about.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.linearcontainer, AboutActivity())
                addToBackStack(null)
                commit()
            }
        }

//        product.setOnClickListener {
//            supportFragmentManager.beginTransaction().apply {
//                replace(R.id.linearcontainer,ProductActivity())
//                addToBackStack(null)
//                commit()
//            }
//        }
//
//    }
    }

}