package com.example.androidproject25b.Entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Appointment (
    @ColumnInfo(name="_id") val _id:String? = null,
    @ColumnInfo(name="device_name") val device_name: String? = null,
    @ColumnInfo(name = "device_model") val device_model: String? = null,
    @ColumnInfo(name = "appointment_date") val appointment_date: String? = null,
    @ColumnInfo(name = "location") val location: String? = null,
    @ColumnInfo(name = "issue") val issue: String? = null,
) :Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="id") var id:Int=0

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
        id = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(device_name)
        parcel.writeString(device_model)
        parcel.writeString(appointment_date)
        parcel.writeString(location)
        parcel.writeString(issue)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Appointment> {
        override fun createFromParcel(parcel: Parcel): Appointment {
            return Appointment(parcel)
        }

        override fun newArray(size: Int): Array<Appointment?> {
            return arrayOfNulls(size)
        }
    }
}
