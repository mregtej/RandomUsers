package com.mregtej.randomusers.network.model

import com.google.gson.annotations.SerializedName

data class LocationData(
    @SerializedName("street") val street: StreetData,
    @SerializedName("city") val city: String,
    @SerializedName("state") val state: String,
    @SerializedName("country") val country: String,
    @SerializedName("postcode") val postcode: String,
    @SerializedName("coordinates") val coordinates: Coordinates,
    @SerializedName("timezone") val timezone: TimeZone
)
