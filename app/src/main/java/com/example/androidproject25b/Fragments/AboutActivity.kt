package com.example.androidproject25b.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidproject25b.LoginActivity
import com.example.androidproject25b.MapsActivity
import com.example.androidproject25b.R

class AboutActivity : Fragment() {
    private lateinit var btnmap : TextView
    private lateinit var logout:TextView
    private lateinit var WebSite:WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_about_activity, container, false)
        btnmap=view.findViewById(R.id.btnnmap)
        logout=view.findViewById(R.id.logout)
        WebSite=view.findViewById(R.id.WebSite)

        WebSite.loadUrl("https://laptopsalesrepair.com/")

//        WebSite.loadUrl("http://10.0.2.2:/")


        val webSettings = WebSite.getSettings()
        webSettings.setJavaScriptEnabled(true)

        logout.setOnClickListener {
            logout()
        }

        btnmap.setOnClickListener {
            startActivity(Intent(this@AboutActivity.context,MapsActivity::class.java))
        }
        return view
    }

    private fun logout() {
        val sharedpref =this.requireContext().getSharedPreferences("mypref", AppCompatActivity.MODE_PRIVATE)
        val editor = sharedpref.edit()
        editor.clear()
        editor.apply()
        startActivity(Intent(this@AboutActivity.context,LoginActivity::class.java))
    }
}