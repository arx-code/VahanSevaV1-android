package com.vahanseva.auto_mall

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class for VahanSeva
 * Annotated with @HiltAndroidApp to enable Hilt dependency injection
 */
@HiltAndroidApp
class VahanSevaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize any app-wide configurations here
    }
}
