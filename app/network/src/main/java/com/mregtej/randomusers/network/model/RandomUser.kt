package com.mregtej.randomusers.network.model

import com.google.gson.annotations.SerializedName

data class RandomUser(
    @SerializedName("gender") val gender: String,
    @SerializedName("name") val name: UserName,
    @SerializedName("location") val location: LocationData,
    @SerializedName("email") val email: String,
    @SerializedName("login") val login: LoginData,
    @SerializedName("dob") val dob: CustomDate,
    @SerializedName("registered") val registered: CustomDate,
    @SerializedName("phone") val phone: String,
    @SerializedName("cell") val cell: String,
    @SerializedName("id") val id: Id,
    @SerializedName("picture") val picture: PictureData,
    @SerializedName("nat") val nationality: String,
)
