/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : app
 * File : App.kt
 *
 * Description:
 * Kelas Application utama yang menginisialisasi Hilt, Timber, dan library native SQLCipher.
 */

package com.muh.arifandi.dicoding

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
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
