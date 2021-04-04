package com.example.androidproject25b

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
//import com.example.androidproject25b.Entity.User
import com.example.androidproject25b.Repository.UserRepository
import com.example.androidproject25b.api.ServiceBuilder
//import com.example.androidproject25b.db.UserDB
//import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class LoginActivity : AppCompatActivity() {


    private val permissions = arrayOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var etsignUp: TextView
    private lateinit var btnLogin: Button
    private lateinit var checkRememberMe: CheckBox
//    private lateinit var linearLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsername = findViewById(R.id.edUserName)
        etPassword = findViewById(R.id.edloginpassword)
        etsignUp = findViewById(R.id.txt_sign_up)
        btnLogin = findViewById(R.id.btnSingIn)
        checkRememberMe = findViewById(R.id.checkBox)
//        linearLayout = findViewById(R.id.linearLayout)

//        etUsername.setText("")
//        etPassword.setText("")

        checkRunTimePermission()
        btnLogin.setOnClickListener {
            login()
//            saveSharePref()

        }
        etsignUp.setOnClickListener {
//            getSharePref()
            startActivity(Intent(this@LoginActivity, RegistrationActivity::class.java))
        }
    }

    private fun checkRunTimePermission() {
        if (!hasPermission()) {
            requestPermission()
        }
    }

    private fun hasPermission(): Boolean {
        var hasPermission = true
        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    permission
                )
                != PackageManager.PERMISSION_GRANTED
            ) {
                hasPermission = false
                break
            }
        }
        return hasPermission
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this@LoginActivity, permissions, 1
        )
    }

//    private fun saveSharePref() {
//        val Username = etUsername.text.toString()
//        val Password = etPassword.text.toString()
//        val sharePref = getSharedPreferences("jetuPref", MODE_PRIVATE)
//        val editor = sharePref.edit()
//        editor.putString("username", Username)
//        editor.putString("password", Password)
//        editor.apply()
//        Toast.makeText(this@LoginActivity, " Username and password saved", Toast.LENGTH_SHORT)
//            .show()
//    }
//
//    private fun getSharePref() {
//        val sharedPref = getSharedPreferences("jetuPref", MODE_PRIVATE)
//        val Username = sharedPref.getString("username", "")
//        val Password = sharedPref.getString("password", "")
//        Toast.makeText(this, "Username :$Username and password : $Password", Toast.LENGTH_SHORT)
//            .show()
//    }

    private fun login() {
        val U_name = etUsername.text.toString()
        val U_password = etPassword.text.toString()

//        var user: User? = null
        CoroutineScope(Dispatchers.IO).launch {

            try {
                val repository = UserRepository()
                val response = repository.loginUser(U_name, U_password)
                if (response.success == true) {
                    ServiceBuilder.token = "Bearer " + response.token

                    startActivity(
                        Intent(
                            this@LoginActivity,
                            NavDrawerActivity::class.java
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


//user = UserDB.getInstance(this@LoginActivity).getUserDAO().checkUser(U_name, U_password)
//
//            if (user == null)
//                withContext(Dispatchers.Main) {
//                    Toast.makeText(this@LoginActivity, "Invalid Credentials", Toast.LENGTH_SHORT)
//                        .show()
//                }
//      else {
//          startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
//            }


//
//                }
//            } catch (ex: Exception) {
////                withContext(Dispatchers.Main) {
////                    Toast.makeText(this@LoginActivity, "Login Fail", Toast.LENGTH_SHORT).show()
////                }
////            }

//            user = UserDB.getInstance(this@LoginActivity)
//                .getUserDAO().checkUser(U_name, U_password)
//
//            if (user == null) {
//                withContext(Dispatchers.Main) {
//                    Toast.makeText(this@LoginActivity, "Invalid credentials", Toast.LENGTH_SHORT)
//                        .show()
//                }
//            } else {
//
//                val intent = (Intent(this@LoginActivity, DashboardActivity::class.java))
//                startActivity(intent)
//            }



//                else {
//                    withContext(Dispatchers.Main) {
//                        val snack = Snackbar.make(
////                            linearLayout,
//                            "Invalid Credentials", Snackbar.LENGTH_LONG
//                        )
//                        snack.setAction("Ok", View.OnClickListener {
//                            snack.dismiss()
//                        })
//                        snack.show()
//                    }

