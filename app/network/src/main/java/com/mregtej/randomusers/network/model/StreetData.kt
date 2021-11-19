package com.mregtej.randomusers.network.model

import com.google.gson.annotations.SerializedName

data class StreetData(
    @SerializedName("number") val number: Int,
    @SerializedName("name") val name: String,
)
