/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : app
 * File : AppNavHost.kt
 *
 * Description:
 * Komponen navigasi utama yang menggabungkan seluruh grafik navigasi dari berbagai modul fitur.
 */
package com.muh.arifandi.dicoding.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.muh.arifandi.dicoding.core.architecture.navigation.FeatureApi
import com.muh.arifandi.dicoding.features.splash.api.SplashDestinations

@Composable
fun AppNavHost(
    navController: NavHostController,
    featureApis: Set<FeatureApi>,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = SplashDestinations,
        modifier = modifier
    ) {
        featureApis.forEach { api ->
            api.registerGraph(this, navController)
        }
    }
}
