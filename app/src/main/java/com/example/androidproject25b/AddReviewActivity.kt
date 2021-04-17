package com.example.androidproject25b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.androidproject25b.Entity.Review
import com.example.androidproject25b.Repository.ReviewRepository
import com.example.androidproject25b.db.ReviewDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class AddReviewActivity : AppCompatActivity() {

    private lateinit var etFeedbackTitle: EditText
    private lateinit var etFeedbackDescription: EditText
    private lateinit var etCustomerName: EditText
    private lateinit var btnAddReview: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_review)

        etCustomerName=findViewById(R.id.etCustomerName)
        etFeedbackTitle=findViewById(R.id.etFeedbackTitle)
        etFeedbackDescription=findViewById(R.id.etFeedbackDescription)
        btnAddReview=findViewById(R.id.btnAddReview)

        btnAddReview.setOnClickListener {
            addReview()
        }
    }

    private fun addReview() {
        val FeedbackTitle = etFeedbackTitle.text.toString()
        val FeedbackDescription = etFeedbackDescription.text.toString()
        val CustomerName = etCustomerName.text.toString()

        val review = Review(
            feedback_title = FeedbackTitle,
            feedback_description = FeedbackDescription,
            customer_name = CustomerName
        )
        CoroutineScope(Dispatchers.IO).launch {
            try {

                ReviewDB
                    .getInstance(this@AddReviewActivity)
                    .getReviewDAO()
                    .insertReview(review)

                val reviewRepository = ReviewRepository()
                val response = reviewRepository.addReview(review)
                if (response.success == true) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@AddReviewActivity,
                            "New Review Added Successfully",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@AddReviewActivity,
                        "Error ${ex.localizedMessage}",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }

        }

    }
}