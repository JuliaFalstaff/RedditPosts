package com.example

import android.app.Application
import com.example.redditposts.di.application
import com.example.redditposts.di.mainScreen
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen))
        }
        Stetho.initializeWithDefaults(this)
    }
}