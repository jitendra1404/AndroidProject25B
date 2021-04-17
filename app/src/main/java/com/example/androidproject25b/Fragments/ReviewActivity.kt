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
import com.example.androidproject25b.Adapter.ReviewAdapter
import com.example.androidproject25b.AddReviewActivity
import com.example.androidproject25b.R
import com.example.androidproject25b.Repository.ReviewRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


class ReviewActivity : Fragment() {

    private lateinit var floatingbutton1: FloatingActionButton
    private lateinit var recyclerView1: RecyclerView
    private lateinit var swipeRefresh1: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_review_activity, container, false)

        recyclerView1=view.findViewById(R.id.recyclerview1)
        swipeRefresh1 = view.findViewById(R.id.swipeRefresh1)
        floatingbutton1=view.findViewById(R.id.floatingActionButton1)

        floatingbutton1.setOnClickListener {
            val intent = Intent (this@ReviewActivity .context, AddReviewActivity::class.java)
            startActivity(intent)
        }

        loadreview()

        dataloading()
        return view
    }

    private fun dataloading() {
        swipeRefresh1.setOnRefreshListener {
            loadreview()
            swipeRefresh1.isRefreshing = false
        }
    }

    private fun loadreview() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val reviewRepository = ReviewRepository()
                val response =  reviewRepository.getAllReview()
                if (response.data !=null){
                    val lstreview = response.data
                    withContext(Dispatchers.Main) {
                        recyclerView1.layoutManager= LinearLayoutManager(activity)
                        recyclerView1.adapter= ReviewAdapter(requireContext(),lstreview)
                    }
                }
            }catch (ex: Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(activity, "Error:${ex.toString()}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}