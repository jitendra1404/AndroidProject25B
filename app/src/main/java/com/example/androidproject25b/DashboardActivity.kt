package com.example.androidproject25b

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)


    }

}
























//    private val permissions = arrayOf(
//            android.Manifest.permission.CAMERA,
//            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
//            android.Manifest.permission.ACCESS_FINE_LOCATION

//    )


//        if (!hasPermission()) {
//            requestPermission()
//
//        }




//    private fun requestPermission() {
//            ActivityCompat.requestPermissions(
//                    this@DashboardActivity,
//                    permissions, 1
//            )
//    }
//    private fun hasPermission(): Boolean {
//        var hasPermission = true
//        for (permission in permissions) {
//            if (ActivityCompat.checkSelfPermission(
//                            this,
//                            permission
//                    ) != PackageManager.PERMISSION_GRANTED
//            ) {
//                hasPermission = false
//            }
//        }
//        return hasPermission
//    }



