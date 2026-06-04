package com.muh.arifandi.dicoding.features.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.muh.arifandi.dicoding.core.architecture.navigation.FeatureApi
import com.muh.arifandi.dicoding.features.login.api.LoginDestinations
import com.muh.arifandi.dicoding.features.login.ui.LoginScreen
import com.muh.arifandi.dicoding.features.register.api.RegisterDestinations
import javax.inject.Inject

class LoginFeatureApiImpl @Inject constructor() : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
    ) {
        navGraphBuilder.composable<LoginDestinations> {
            LoginScreen(
                onNavigateToHome = {
                    // TODO: Implement navigation to home after login
                    // navController.navigate(MasterDestinations) {
                    //     popUpTo(LoginDestinations) { inclusive = true }
                    // }
                },
                onNavigateToRegister = {
                    navController.navigate(RegisterDestinations)
                }
            ) {
                // TODO: Implement navigation to forgot password
                // navController.navigate(ForgotpasswordDestinations.Request)
            }
        }
    }
}
