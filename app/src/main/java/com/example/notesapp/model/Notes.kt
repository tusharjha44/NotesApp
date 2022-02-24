package com.example.notesapp.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var title: String,
    var subtitle: String,
    var notes: String,
    var date: String,
    var priority: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(title)
        parcel.writeString(subtitle)
        parcel.writeString(notes)
        parcel.writeString(date)
        parcel.writeString(priority)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Notes> {
        override fun createFromParcel(parcel: Parcel): Notes {
            return Notes(parcel)
        }

        override fun newArray(size: Int): Array<Notes?> {
            return arrayOfNulls(size)
        }
    }
}