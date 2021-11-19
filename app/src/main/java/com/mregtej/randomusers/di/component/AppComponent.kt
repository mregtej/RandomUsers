package com.mregtej.randomusers.di.component

import com.mregtej.randomusers.MainActivity
import com.mregtej.randomusers.di.App
import com.mregtej.randomusers.di.module.AppModule
import com.mregtej.randomusers.di.module.DatabaseModule
import com.mregtej.randomusers.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, AppModule::class, DatabaseModule::class])
interface AppComponent {
    fun inject(app: App)
    fun inject(mainActivity: MainActivity)
}