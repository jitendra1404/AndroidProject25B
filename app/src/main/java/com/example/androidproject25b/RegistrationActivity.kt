package com.example.androidproject25b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        var username : EditText = findViewById(R.id.edUsername)
        var email : EditText =findViewById(R.id.edEmail)
        var mobile : EditText =findViewById(R.id.edmobile)
        var address : EditText =findViewById(R.id.edAddress)
        var password : EditText =findViewById(R.id.edPassword)
        var signUp : Button =findViewById(R.id.btnSingUP)

        signUp.setOnClickListener{

            var intent = Intent(this@RegistrationActivity, LoginActivity::class.java)
            startActivity(intent)


            Toast.makeText(this, "SignUp success", Toast.LENGTH_SHORT).show()
        }
    }
}

