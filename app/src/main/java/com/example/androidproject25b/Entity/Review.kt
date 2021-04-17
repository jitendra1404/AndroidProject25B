package com.example.androidproject25b.Entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Review (

@ColumnInfo(name="_id") val _id:String? = null,
@ColumnInfo(name="feedback_title") val feedback_title: String? = null,
@ColumnInfo(name = "feedback_description") val feedback_description: String? = null,
@ColumnInfo(name = "customer_name") val  customer_name: String? = null,

) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="id") var id:Int=0

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
        id = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(feedback_title)
        parcel.writeString(feedback_description)
        parcel.writeString(customer_name)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Review> {
        override fun createFromParcel(parcel: Parcel): Review {
            return Review(parcel)
        }

        override fun newArray(size: Int): Array<Review?> {
            return arrayOfNulls(size)
        }
    }
}
