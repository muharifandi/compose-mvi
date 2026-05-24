/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : app
 * File : MainActivity.kt
 *
 * Description:
 * Entry point utama aplikasi yang menginisialisasi Compose, Navigasi, dan pengecekan integritas keamanan.
 */

package com.muh.arifandi.dicoding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.rememberNavController
import com.muh.arifandi.dicoding.core.architecture.navigation.FeatureApi
import com.muh.arifandi.dicoding.core.common.navigation.NavigationManager
import com.muh.arifandi.dicoding.core.common.security.SecurityGuard
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme
import com.muh.arifandi.dicoding.navigation.AppNavHost
import com.muh.arifandi.dicoding.navigation.delegate.NavigationHandler
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var featureApis: Set<@JvmSuppressWildcards FeatureApi>

    @Inject
    lateinit var navigationManager: NavigationManager

    @Inject
    lateinit var navigationHandler: NavigationHandler

    @Inject
    lateinit var securityGuard: SecurityGuard

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        
        securityGuard.checkIntegrity(BuildConfig.DEBUG) { reason ->
            Timber.e("App terminated due to: $reason")
            finish()
        }

        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()

                LaunchedEffect(navController) {
                    lifecycleScope.launch {
                        repeatOnLifecycle(Lifecycle.State.STARTED) {
                            navigationManager.navigationCommands.collect { command ->
                                navigationHandler.handle(command, navController)
                            }
                        }
                    }
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    AppNavHost(
                        navController = navController,
                        featureApis = featureApis
                    )
                }
            }
        }
    }
}
