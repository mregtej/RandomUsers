package com.mregtej.randomusers.network.model

import com.google.gson.annotations.SerializedName

data class UserName(
    @SerializedName("title") val title: String,
    @SerializedName("first") val firstName: String,
    @SerializedName("last") val lastName: String
)
