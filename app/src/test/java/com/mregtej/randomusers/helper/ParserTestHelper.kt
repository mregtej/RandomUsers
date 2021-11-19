package com.mregtej.randomusers.helper

import com.google.gson.Gson
import com.mregtej.randomusers.network.model.ResponseBody
import java.io.InputStream
import java.text.ParseException

object ParserTestHelper {

    private val gSon = Gson()

    val expectedUsersFromJson: ResponseBody = usersFromJson(ServiceTestHelper.getSuccessResponse())
    val expectedEmptyUsersFromJson: ResponseBody = usersFromJson(ServiceTestHelper.getEmptyResponse())

    private fun usersFromJson(inputStream: InputStream) = try {
        inputStream.bufferedReader().use { gSon.fromJson(it, ResponseBody::class.java) }
    } catch (e: Exception) {
        throw ParseException("Could not parse ${ResponseBody::class.java.simpleName}", 0)
    }
}