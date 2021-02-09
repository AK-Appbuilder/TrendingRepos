package com.boonapps.repos

import android.app.Application
import android.content.Context

class App : Application() {

    override fun onCreate() {
        instance = this
        super.onCreate()
    }

    companion object {
        lateinit var  instance: App
            private set

        val context: Context
            get() = instance
    }
}