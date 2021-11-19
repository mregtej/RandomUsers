package com.mregtej.randomusers.network.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class CustomDate(
    @SerializedName("date") val date: Date,
    @SerializedName("age") val age: Int
)