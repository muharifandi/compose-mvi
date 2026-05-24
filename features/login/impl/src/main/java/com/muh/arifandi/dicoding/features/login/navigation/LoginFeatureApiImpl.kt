package com.muh.arifandi.dicoding.features.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.muh.arifandi.dicoding.core.architecture.navigation.FeatureApi
import com.muh.arifandi.dicoding.features.login.api.LoginDestinations
import com.muh.arifandi.dicoding.features.login.ui.LoginScreen
import com.muh.arifandi.dicoding.features.register.api.RegisterDestinations
import com.muh.arifandi.dicoding.features.forgotpassword.api.ForgotpasswordDestinations
import com.muh.arifandi.dicoding.features.master.api.MasterDestinations
import javax.inject.Inject

class LoginFeatureApiImpl @Inject constructor() : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
    ) {
        navGraphBuilder.composable<LoginDestinations> {
            LoginScreen(
                onNavigateToHome = {
                    navController.navigate(MasterDestinations) {
                        popUpTo(LoginDestinations) { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(RegisterDestinations)
                }
            ) {
                navController.navigate(ForgotpasswordDestinations.Request)
            }
        }
    }
}
