package com.mregtej.randomusers.database.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RandomUserDetailsData(
    @PrimaryKey
    @ColumnInfo(name = "uuid")
    val uuid: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "photoUrl")
    val photoUrl: String,
    @ColumnInfo(name = "gender")
    val gender: String,
    @ColumnInfo(name = "location")
    val location: String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "phone")
    val phone: String,
    @ColumnInfo(name = "registered_date")
    val registeredDate: String
): Parcelable
