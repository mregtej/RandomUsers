package com.mregtej.randomusers.network.service

import com.mregtej.randomusers.network.model.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("https://api.randomuser.me/")
    suspend fun getRandomUsers(@Query("results") results: Int): ResponseBody
}