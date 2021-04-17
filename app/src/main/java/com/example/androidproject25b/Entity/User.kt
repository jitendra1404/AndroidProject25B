package com.example.androidproject25b.Entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
 class User(
    @PrimaryKey
        val _id: String="",
        var custo_name :String? =null,
        var custo_address : String?=null,
        var custo_email: String?=null,
        var custo_mobile:String?=null,
         var custo_password:String?=null

)
