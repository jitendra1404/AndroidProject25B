package com.example.androidproject25b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var Userlogin : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Userlogin =findViewById(R.id.btnLogin)



        Userlogin.setOnClickListener{

            var intent = Intent(this@MainActivity, SplashActivity::class.java)
            startActivity(intent)

            Toast.makeText(this, "welcome User login", Toast.LENGTH_SHORT).show()


        }
    }
}