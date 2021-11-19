package com.mregtej.randomusers.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.mregtej.randomusers.database.model.RandomUser
import com.mregtej.randomusers.database.model.RandomUserDetailsData
import com.mregtej.randomusers.database.model.RandomUserListData

@Dao
interface RandomUserDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertUser(data: RandomUser)

    @Query("SELECT uuid, name, email, photoUrl, phone FROM users")
    fun getUsers(): List<RandomUserListData>

    @Query("SELECT uuid, name, photoUrl, gender, location, email, phone, registered_date FROM users WHERE uuid == :uuid")
    fun getDetailedUserById(uuid: String): RandomUserDetailsData

    @Query("SELECT COUNT(*) FROM users")
    fun getUserNumber(): Int
}