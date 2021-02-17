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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        var username : EditText = findViewById(R.id.edUsername)
        var email : EditText =findViewById(R.id.edEmail)
        var mobile : EditText =findViewById(R.id.edmobile)
        var address : EditText =findViewById(R.id.edAddress)
        var password : EditText =findViewById(R.id.edPassword)
        var confimrpassword :EditText=findViewById(R.id.edConfirmPassword)
        var signUp : Button =findViewById(R.id.btnSingUP)

        signUp.setOnClickListener{

            val Name = username.text.toString()
            val Address = address.text.toString()
            val Mobile =mobile.text.toString()
            val Email = email.text.toString()
            val Password = password.text.toString()
            val ConfirmPassword =confimrpassword.text.toString()

            if (Password != ConfirmPassword) {
                password.error = "password does not match"
                password.requestFocus()
                return@setOnClickListener

            }else {
                val user =User(Name, Address, Mobile, Email, Password)
                CoroutineScope(Dispatchers.IO).launch {
                    UserDB.getInstance(this@RegistrationActivity).getUserDAO().resgisterUser(user)
                }
                Toast.makeText(this, "User sinUp", Toast.LENGTH_SHORT).show()
            }


        }
    }
}

