/**
 * Created by Muh. Arifandi on 07/05/26.
 * Email : arif76440@gmail.com
 * Project: My Application
 * File: MainActivity
 */
/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : app
 * File : MainActivity.kt
 *
 * Description:
 * Entry point utama aplikasi yang menginisialisasi Compose dan Navigasi.
 */

package com.muh.arifandi.dicoding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.muh.arifandi.dicoding.core.common.navigation.NavigationCommand
import com.muh.arifandi.dicoding.core.common.navigation.NavigationManager
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme
import com.muh.arifandi.dicoding.navigation.AppNavHost
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()

                LaunchedEffect(Unit) {
                    navigationManager.navigationCommands.collect { command ->
                        when (command) {
                            is NavigationCommand.NavigateTo -> navController.navigate(command.route)
                            is NavigationCommand.NavigateBack -> navController.popBackStack()
                            is NavigationCommand.NavigateAndPopUpTo -> {
                                navController.navigate(command.route) {
                                    popUpTo(command.popUpTo) { inclusive = command.inclusive }
                                }
                            }
                        }
                    }
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavHost(navController = navController)
                }
            }
        }
    }
}
