package com.example.androidproject25b.response


import com.example.androidproject25b.Entity.Review

data class AddReviewResponse (
    val success:Boolean?=null,
    val data: Review?=null
        )
