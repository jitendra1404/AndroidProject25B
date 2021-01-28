package com.example.androidproject25b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class LoginActivity : AppCompatActivity() {

    private lateinit var etUsername :EditText
    private lateinit var etPassword :EditText
    private lateinit var etsignUp :TextView
    private lateinit var btnLogin :Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsername =findViewById(R.id.edUserName)
        etPassword =findViewById(R.id.edloginpassword)
        etsignUp =findViewById(R.id.txt_sign_up)
        btnLogin =findViewById(R.id.btnSingIn)
        etUsername.setText("")
        etPassword.setText("")

        btnLogin.setOnClickListener {
            login()
        }
        etsignUp.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegistrationActivity::class.java))
        }
    }

    private fun login(){
        val U_name = etUsername.text.toString()
        val U_passworc =etPassword.text.toString()

    }
}