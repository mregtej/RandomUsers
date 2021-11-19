package com.mregtej.randomusers.database.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class RandomUserListData(
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
    val phone: String
)
