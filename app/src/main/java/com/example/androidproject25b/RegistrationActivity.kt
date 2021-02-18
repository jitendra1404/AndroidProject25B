package com.example.androidproject25b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.androidproject25b.Entity.User
import com.example.androidproject25b.Repository.UserRepository
import com.example.androidproject25b.api.ServiceBuilder
import com.example.androidproject25b.db.UserDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

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
                Toast.makeText(this, "AAYO KALO KETA", Toast.LENGTH_SHORT).show()
                CoroutineScope(Dispatchers.IO).launch {

                    val repository=UserRepository()
                    try {
                        val response= repository.registerUser(user)
                        if (response.success==true){
                            ServiceBuilder.token == "Bearer " +response.token
                            withContext(Main){
                                Toast.makeText(this@RegistrationActivity, "Successfully registered", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }catch (ex:Exception){
                        withContext(Main){
                            Toast.makeText(this@RegistrationActivity, "Register Fail", Toast.LENGTH_SHORT).show()
                        }
                    }

                    UserDB.getInstance(this@RegistrationActivity).getUserDAO().resgisterUser(user)
                }
                Toast.makeText(this, "User sinUp", Toast.LENGTH_SHORT).show()
            }


        }
    }
}

