package com.example.a20202212arielapplebaumnycschoolskotlin.data

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tbl_Schools")
@Parcelize
data class School(
    val dbn : String,
    val name : String,
    val phone : String,
    val email : String,
    val SATCriticalReading : String,
    val SATMath : String,
    val SATWriting : String
) : Parcelable