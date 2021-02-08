package com.boonapps.repos

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho

class App : Application() {
    override fun onCreate() {
        instance = this
        super.onCreate()

        Stetho.initializeWithDefaults(this)
    }

    companion object {
        lateinit var  instance: App
            private set

        val context: Context
            get() = instance
    }
}