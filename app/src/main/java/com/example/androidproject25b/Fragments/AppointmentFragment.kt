package com.example.androidproject25b.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.androidproject25b.R
import kotlinx.android.synthetic.main.fragment_appointment.*


/**
 * A simple [Fragment] subclass.
 */
class AppointmentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_appointment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvAppointment.setOnClickListener {
            Toast.makeText(context, "Appointment", Toast.LENGTH_SHORT).show()
        }
    }
}