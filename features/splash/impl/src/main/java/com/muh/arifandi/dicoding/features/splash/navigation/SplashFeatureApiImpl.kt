package com.muh.arifandi.dicoding.features.splash.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.muh.arifandi.dicoding.core.architecture.navigation.FeatureApi
import com.muh.arifandi.dicoding.features.login.api.LoginDestinations
import com.muh.arifandi.dicoding.features.splash.api.SplashDestinations
import com.muh.arifandi.dicoding.features.splash.ui.SplashScreen
import javax.inject.Inject

class SplashFeatureApiImpl @Inject constructor() : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    ) {
        navGraphBuilder.composable<SplashDestinations> {
            SplashScreen(
                onNavigateToLogin = {
                    navController.navigate(LoginDestinations) {
                        popUpTo(SplashDestinations) { inclusive = true }
                    }
                }
            )
        }
    }
}
