package com.mregtej.randomusers.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class RandomUser(
    @PrimaryKey
    @ColumnInfo(name = "uuid")
    val uuid: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "photoUrl")
    val photoUrl: String,
    @ColumnInfo(name = "phone")
    val phone: String,
    @ColumnInfo(name = "gender")
    val gender: String,
    @ColumnInfo(name = "location")
    val location: String,
    @ColumnInfo(name = "registered_date")
    val registeredDate: String
)
