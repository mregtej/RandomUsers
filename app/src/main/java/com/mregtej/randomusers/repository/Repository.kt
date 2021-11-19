package com.mregtej.randomusers.repository

import com.mregtej.randomusers.database.model.RandomUserDetailsData
import com.mregtej.randomusers.database.model.RandomUserListData

interface Repository {

    @Throws(Exception::class)
    suspend fun getUsers(): List<RandomUserListData>

    @Throws(Exception::class)
    suspend fun addNewUsers(): List<RandomUserListData>

    @Throws(Exception::class)
    suspend fun getDetailedUser(uuid: String): RandomUserDetailsData
}