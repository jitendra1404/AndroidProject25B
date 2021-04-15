package com.example.androidproject25b

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.androidproject25b.R
import androidx.viewpager2.widget.ViewPager2
import com.example.androidproject25b.Adapter.ViewPagerAdapter
import com.example.androidproject25b.Fragments.AboutActivity
import com.example.androidproject25b.Fragments.AppointmentActivity
import com.example.androidproject25b.Fragments.DashboardActivity
import com.example.androidproject25b.Fragments.ReviewActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TabActivity : AppCompatActivity() {

    private val permissions = arrayOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )

    private lateinit var lstTitle:ArrayList<String>
    private lateinit var lstFragments:ArrayList<Fragment>
    private lateinit var viewpager: ViewPager2
    private lateinit var tablayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab)

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
        lstTitle.add("Appointment")
        lstTitle.add("Review")
        lstTitle.add("About")
        lstFragments=ArrayList<Fragment>()
        lstFragments.add(DashboardActivity())
        lstFragments.add(AppointmentActivity())
        lstFragments.add(ReviewActivity())
        lstFragments.add(AboutActivity())
    }

    }




