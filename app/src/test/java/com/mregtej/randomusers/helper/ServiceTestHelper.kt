package com.mregtej.randomusers.helper

import com.google.common.base.Charsets.UTF_8
import com.google.common.io.Resources.toString
import com.mregtej.randomusers.helper.ServiceTestHelper.Constants.EMPTY_JSON_FILE
import com.mregtej.randomusers.helper.ServiceTestHelper.Constants.USERS_JSON_FILE
import com.mregtej.randomusers.network.service.Service
import java.io.StringReader

object ServiceTestHelper {

    fun getSuccessResponse() =
        contentOfFile(USERS_JSON_FILE).readText().byteInputStream()

    fun getEmptyResponse() =
        contentOfFile(EMPTY_JSON_FILE).readText().byteInputStream()

    private fun contentOfFile(path: String): StringReader {
        val response = checkNotNull(Service::class.java.classLoader?.getResource(path))
        return StringReader(toString(response, UTF_8))
    }

    object Constants {
        const val USERS_JSON_FILE = "test/users.json"
        const val EMPTY_JSON_FILE = "test/empty.json"
    }
}