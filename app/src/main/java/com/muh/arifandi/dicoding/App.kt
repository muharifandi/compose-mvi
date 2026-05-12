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
        
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
