package com.example.androidproject25b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var usergo : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usergo =findViewById(R.id.btnGo)


        usergo.setOnClickListener{

            var intent = Intent(this@MainActivity, SplashActivity::class.java)
            startActivity(intent)

            Toast.makeText(this, "welcome User login", Toast.LENGTH_SHORT).show()


        }
    }
}