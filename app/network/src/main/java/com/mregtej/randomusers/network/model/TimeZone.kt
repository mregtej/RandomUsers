package com.mregtej.randomusers.network.model

import com.google.gson.annotations.SerializedName

data class TimeZone(
    @SerializedName("offset") val offset: String,
    @SerializedName("description") val description: String
)
