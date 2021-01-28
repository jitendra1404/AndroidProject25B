package com.example.androidproject25b.Entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
        var username : String? =null,
        var address : String?=null,
        var mobile: String?=null,
        var Email:String?=null,
         var password:String?=null

):Parcelable{
    @PrimaryKey(autoGenerate=true)
    var userId: Int =0

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
        userId = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(username)
        parcel.writeString(address)
        parcel.writeString(mobile)
        parcel.writeString(Email)
        parcel.writeString(password)
        parcel.writeInt(userId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}
