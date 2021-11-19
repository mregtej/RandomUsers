package com.mregtej.randomusers.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mregtej.randomusers.database.dao.RandomUserDao
import com.mregtej.randomusers.database.model.RandomUser

@Database(entities = [RandomUser::class], version = 1)
abstract class RandomUserDatabase : RoomDatabase() {

    abstract fun randomUserDao(): RandomUserDao

    companion object {
        @Volatile
        private var instance: RandomUserDatabase? = null
        private val LOCK = Any()

        private fun buildDatabase(
            context: Context,
            databaseName: String
        ) =
            Room.databaseBuilder(
                context.applicationContext,
                RandomUserDatabase::class.java,
                databaseName
            ).build()

        operator fun invoke(
            context: Context,
            databaseName: String
        ) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context, databaseName).also { instance = it }
        }
    }
}