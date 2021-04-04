package com.example.androidproject25b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class NavDrawerActivity : AppCompatActivity() {

    lateinit var  toggle: ActionBarDrawerToggle
    lateinit var drawerLayout:DrawerLayout
    lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_drawer)

        drawerLayout=findViewById(R.id.DrawerLayout)
        navigationView=findViewById(R.id.nav_view)

        toggle= ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
               R.id.Dashboard -> Toast.makeText(applicationContext, "Dashboard clicked", Toast.LENGTH_SHORT).show()
                R.id.Home -> Toast.makeText(applicationContext, "Home clicked", Toast.LENGTH_SHORT).show()
                R.id.aboutus -> Toast.makeText(applicationContext, "AboutUs clicked", Toast.LENGTH_SHORT).show()
                R.id.appointment -> Toast.makeText(applicationContext, "Appointment clicked", Toast.LENGTH_SHORT).show()
                R.id.product -> Toast.makeText(applicationContext, "Product clicked", Toast.LENGTH_SHORT).show()
                R.id.Logout -> Toast.makeText(applicationContext, "Logout clicked", Toast.LENGTH_SHORT).show()
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){

            true
        }
        return super.onOptionsItemSelected(item)
    }
}