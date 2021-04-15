package com.example.laptoprepairwearos

import android.content.Intent
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.laptoprepairwear.Repository.UserRepository
import com.example.laptoprepairwear.api.ServiceBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class LoginActivity : WearableActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsername = findViewById(R.id.edUserName)
        etPassword = findViewById(R.id.edloginpassword)
        btnLogin = findViewById(R.id.btnSingIn)

        btnLogin.setOnClickListener {
            login()

        }

        // Enables Always-on
        setAmbientEnabled()

    }

    private fun login() {
        val U_name = etUsername.text.toString()
        val U_password = etPassword.text.toString()

        CoroutineScope(Dispatchers.IO).launch {

            try {
                val repository = UserRepository()
                val response = repository.loginUser(U_name, U_password)
                if (response.success == true)
                    withContext(Dispatchers.Main){
                        ServiceBuilder.token = "Bearer " + response.token

                        startActivity(
                            Intent(
                                this@LoginActivity,
                                DashboardActivity::class.java
                            )
                        )
                        finish()
                    }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@LoginActivity,
                        ex.toString(), Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }
    }
}
