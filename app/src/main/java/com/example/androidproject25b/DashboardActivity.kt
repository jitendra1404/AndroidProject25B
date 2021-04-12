package com.example.androidproject25b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.widget.ImageViewCompat
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    private lateinit var googlemap :AppCompatImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        googlemap =findViewById(R.id.btnGoogleMap)

        googlemap.setOnClickListener {
            startActivity(Intent(this@DashboardActivity,MapsActivity::class.java))
        }
    }
}