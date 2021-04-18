package com.example.androidproject25b.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.androidproject25b.Adapter.AppointmentAdapter
import com.example.androidproject25b.AddAppointmentActivity
import com.example.androidproject25b.R
import com.example.androidproject25b.Repository.AppointmentRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_appointment_activity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


class AppointmentActivity : Fragment() {

    private lateinit var floatingbutton:FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_appointment_activity, container, false)
        recyclerView=view.findViewById(R.id.recyclerview)
        swipeRefresh = view.findViewById(R.id.swipeRefresh)
        floatingbutton=view.findViewById(R.id.floatingActionButton)

        floatingbutton.setOnClickListener {
            val intent = Intent (this@AppointmentActivity .context, AddAppointmentActivity::class.java)
            startActivity(intent)
        }

        loadappointment()


        dataloading()
        return view
    }

    private fun dataloading() {
        swipeRefresh.setOnRefreshListener {
            loadappointment()
            swipeRefresh.isRefreshing = false
        }
    }

    private fun loadappointment() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val appointmentRepository = AppointmentRepository()
                val response =  appointmentRepository.getAllAppointment()
                if (response.data !=null){
                    val lstappointment = response.data
                    withContext(Dispatchers.Main) {
                        recyclerView.layoutManager=LinearLayoutManager(activity)
                        recyclerView.adapter=AppointmentAdapter(requireContext(),lstappointment)
                    }
                }
            }catch (ex:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(activity, "Error:${ex.toString()}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}