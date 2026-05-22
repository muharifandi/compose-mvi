package com.muh.arifandi.dicoding

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by Muh. Arifandi on 06/05/26.
 * Email : arif76440@gmail.com
 * Project: My Application
 * File: App
 */
@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        
        // Load SQLCipher native library for 16 KB page alignment compatibility (v4.6.1+)
        System.loadLibrary("sqlcipher")
        
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
