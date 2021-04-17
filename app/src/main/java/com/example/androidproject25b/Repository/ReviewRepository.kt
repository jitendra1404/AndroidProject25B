package com.example.androidproject25b.Repository

import com.example.androidproject25b.Entity.Review
import com.example.androidproject25b.api.MyAPIRequest
import com.example.androidproject25b.api.ServiceBuilder
import com.example.androidproject25b.response.AddReviewResponse
import com.example.androidproject25b.response.DeleteReviewResponse
import com.example.androidproject25b.response.GetAllReviewResponse

class ReviewRepository:MyAPIRequest() {

    private val ReviewAPI =
        ServiceBuilder.buildServices(com.example.androidproject25b.api.ReviewAPI::class.java)

      //Add Review
    suspend fun addReview(review: Review): AddReviewResponse {
        return apiRequest {
            ReviewAPI.addReview(
                ServiceBuilder.token!!, review
            )
        }
    }

//    //Delete Review

    suspend fun deleteReview(id: String): DeleteReviewResponse {
        return apiRequest {
            ReviewAPI.deleteReview(
                ServiceBuilder.token!!, id
            )
        }
    }

    // Get Review

    suspend fun getAllReview(): GetAllReviewResponse {
        return apiRequest {
            ReviewAPI.getAllReview(
                ServiceBuilder.token!!
            )
        }
    }

//    //upload image
//    suspend fun uploadImage(id: String,body: MultipartBody.Part)
//            : ImageResponse {
//        return apiRequest {
//            DonorAPI.uploadImage(ServiceBuilder.token!!,id,body)
//        }
//    }
//}


}