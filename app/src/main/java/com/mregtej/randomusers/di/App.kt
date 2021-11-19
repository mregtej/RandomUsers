package com.mregtej.randomusers.di

import android.app.Application
import com.mregtej.randomusers.di.component.AppComponent
import com.mregtej.randomusers.di.component.AppComponentProvider
import com.mregtej.randomusers.di.component.DaggerAppComponent
import com.mregtej.randomusers.di.module.DatabaseModule
import com.mregtej.randomusers.di.module.NetworkModule

class App : Application(), AppComponentProvider {

    private var appComponent: AppComponent? = null

    private val baseUrl = "https://api.randomuser.me/"
    private val databaseName = "RandomUserDatabase.db"

    override fun onCreate() {
        super.onCreate()
        getAppComponent()?.inject(this)
    }

    override fun getAppComponent(): AppComponent? {
        if (appComponent == null) {
            appComponent = DaggerAppComponent
                .builder()
                .networkModule(NetworkModule(baseUrl))
                .databaseModule(DatabaseModule(applicationContext, databaseName))
                .build()
        }
        return appComponent
    }
}