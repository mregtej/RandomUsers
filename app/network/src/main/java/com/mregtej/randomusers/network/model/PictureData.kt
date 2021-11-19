package com.mregtej.randomusers.network.model

import com.google.gson.annotations.SerializedName

data class PictureData(
    @SerializedName("large") val large: String,
    @SerializedName("medium") val medium: String,
    @SerializedName("thumbnail") val thumbnail: String,
)
