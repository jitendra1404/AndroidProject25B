package com.example.androidproject25b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var Userlogin : Button
    private lateinit var Adminlogin : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Userlogin =findViewById(R.id.btnUser_Login)
        Adminlogin =findViewById(R.id.btnAdmin_Login)


        Userlogin.setOnClickListener{

            var   intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)

            Toast.makeText(this, "welcome User login", Toast.LENGTH_SHORT).show()


        }
    }
}