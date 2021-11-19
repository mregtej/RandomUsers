package com.mregtej.randomusers.network.model

import com.google.gson.annotations.SerializedName

data class ResponseBody (
    @SerializedName("results") val results: List<RandomUser>? = null
)