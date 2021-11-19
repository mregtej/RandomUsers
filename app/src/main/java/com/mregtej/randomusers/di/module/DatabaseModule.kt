package com.mregtej.randomusers.di.module

import android.content.Context
import androidx.room.Room
import com.mregtej.randomusers.database.RandomUserDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule(
    private val applicationContext: Context,
    private val databaseName: String
) {
    @Singleton
    @Provides
    fun provideRandomUserDatabase() = Room.databaseBuilder(
        applicationContext,
        RandomUserDatabase::class.java,
        databaseName
    ).build()

    @Singleton
    @Provides
    fun provideRandomUserDao(db: RandomUserDatabase) = db.randomUserDao()
}