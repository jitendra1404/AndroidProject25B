package com.example.androidproject25b

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.androidproject25b.R
import androidx.viewpager2.widget.ViewPager2
import com.example.androidproject25b.Adapter.ViewPagerAdapter
import com.example.androidproject25b.Fragments.*
import com.example.androidproject25b.Fragments.DashboardActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.io.IOException

class TabActivity : AppCompatActivity()
//    ,SensorEventListener
{

    private val permissions = arrayOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )

//    private lateinit var sensorManager: SensorManager
//    private lateinit var sensor:Sensor

    private lateinit var lstTitle:ArrayList<String>
    private lateinit var lstFragments:ArrayList<Fragment>
    private lateinit var viewpager: ViewPager2
    private lateinit var tablayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab)

//        sensorManager=getSystemService(Context.SENSOR_SERVICE)as SensorManager
//        sensor=sensorManager!!.getDefaultSensor(Sensor.TYPE_PROXIMITY)

        if (!hasPermission()){
            requestPermission()
        }

        viewpager=findViewById(R.id.viewpager)
        tablayout=findViewById(R.id.tablayout)


        populateList()
        val adapter = ViewPagerAdapter(lstFragments,supportFragmentManager,lifecycle)
        viewpager.adapter = adapter
        TabLayoutMediator(tablayout,viewpager) {tab,position ->
            tab.text=lstTitle[position]
        }.attach()
    }

    private fun hasPermission(): Boolean {
        var hasPermission = true
        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                hasPermission = false
            }
        }
        return hasPermission
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this@TabActivity,
            permissions,1
        )
    }

    private fun populateList() {
        lstTitle=ArrayList<String>()
        lstTitle.add("Dashboard")
//        lstTitle.add("Product")
        lstTitle.add("Appointment")
        lstTitle.add("Review")
        lstTitle.add("About")
        lstFragments=ArrayList<Fragment>()
        lstFragments.add(DashboardActivity())
//        lstFragments.add(ProductActivity())
        lstFragments.add(AppointmentActivity())
        lstFragments.add(ReviewActivity())
        lstFragments.add(AboutActivity())
    }

//    override fun onResume() {
//        super.onResume()
//        sensorManager!!.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
//    }
//    override fun onPause() {
//        super.onPause()
//        sensorManager!!.unregisterListener(this)
//    }
//
//    override fun onSensorChanged(event: SensorEvent?) {
//        val values = event!!.values[0]
//        if (values <= 4) {
//            try {
//                logoutapp()
//            } catch (e: IOException) {
//            }
//        }
//    }
//
//    private fun logoutapp() {
//        val sharedpref =getSharedPreferences("mypref", AppCompatActivity.MODE_PRIVATE)
//        val editor = sharedpref.edit()
//        editor.clear()
//        editor.apply()
//        startActivity(Intent(this@TabActivity,LoginActivity::class.java))
//    }
//
//    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
//
//    }

}




