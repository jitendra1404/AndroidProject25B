package com.example.androidproject25b.response


import com.example.androidproject25b.Entity.Review

data class GetAllReviewResponse (
    val success:Boolean?=null,
    val count:Int?=null,
    val data :MutableList<Review>?=null
        )
