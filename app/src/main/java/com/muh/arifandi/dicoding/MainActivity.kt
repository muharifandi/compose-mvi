/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : app
 * File : MainActivity.kt
 */

package com.muh.arifandi.dicoding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.muh.arifandi.dicoding.core.common.security.SecurityGuard
import com.muh.arifandi.dicoding.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var securityGuard: SecurityGuard

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        
        securityGuard.checkIntegrity(BuildConfig.DEBUG) { reason ->
            Timber.e("App terminated due to: $reason")
            finish()
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
