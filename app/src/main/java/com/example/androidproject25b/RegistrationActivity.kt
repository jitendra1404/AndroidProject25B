package com.example.androidproject25b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.androidproject25b.Entity.User
import com.example.androidproject25b.db.UserDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrationActivity : AppCompatActivity() {

    private lateinit var username:EditText
    private lateinit var password:EditText
    private lateinit var address:EditText
    private lateinit var mobile:EditText
    private lateinit var email:EditText
    private lateinit var confirmpassword:EditText
    private lateinit var signUp:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        username = findViewById(R.id.edUsername)
        email = findViewById(R.id.edEmail)
        mobile = findViewById(R.id.edmobile)
        address = findViewById(R.id.edAddress)
        password = findViewById(R.id.edPassword)
        confirmpassword = findViewById(R.id.edConfirmPassword)
        signUp = findViewById(R.id.btnSingUP)

        signUp.setOnClickListener{

            val username = username.text.toString()
            val address = address.text.toString()
            val mobile =mobile.text.toString()
            val email = email.text.toString()
            val Password = password.text.toString()
            val ConfirmPassword =confirmpassword.text.toString()

            if (Password != ConfirmPassword) {
                password.error = "password does not match"
                password.requestFocus()
                return@setOnClickListener

            }else {
                val user =User(username= username, address = address, mobile = mobile, email = email, password = Password)
                CoroutineScope(Dispatchers.IO).launch {
                    UserDB.getInstance(this@RegistrationActivity).getUserDAO().resgisterUser(user)
                }
                Toast.makeText(this, "User sinUp", Toast.LENGTH_SHORT).show()
            }


        }
    }
}

