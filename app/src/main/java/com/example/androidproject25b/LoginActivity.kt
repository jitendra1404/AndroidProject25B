package com.example.androidproject25b

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.androidproject25b.Entity.User
import com.example.androidproject25b.db.UserDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

            saveSharePref()

            login()
        }
        etsignUp.setOnClickListener {

            getSharePref()


        }

    }
    private fun saveSharePref(){
        val Username =etUsername.text.toString()
        val Password =etPassword.text.toString()
        val sharePref =getSharedPreferences("jetuPref",MODE_PRIVATE)
        val editor =sharePref.edit()
        editor.putString("username", Username)
        editor.putString("password", Password)
        editor.apply()
        Toast.makeText(this@LoginActivity, " Username and password saved", Toast.LENGTH_SHORT).show()
    }

    private fun getSharePref(){
        val sharedPref = getSharedPreferences("jetuPref",MODE_PRIVATE)
        val Username =sharedPref.getString("username", "")
        val Password =sharedPref.getString("password", "")
        Toast.makeText(this, "Username :$Username and password : $Password", Toast.LENGTH_SHORT).show()
    }

    private fun login(){
        val U_name = etUsername.text.toString()
        val U_password =etPassword.text.toString()

        var user:User?=null
        CoroutineScope(Dispatchers.IO).launch {
            user =UserDB.getInstance(this@LoginActivity)
                .getUserDAO().checkUser(U_name, U_password)

            if (user==null) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@LoginActivity, "Invalid credentials", Toast.LENGTH_SHORT)
                        .show()
                }
            }else {

                val intent=(Intent(this@LoginActivity, DashboardActivity::class.java))
                startActivity(intent)
            }

            startActivity(Intent(this@LoginActivity, RegistrationActivity::class.java))

        }


    }
}