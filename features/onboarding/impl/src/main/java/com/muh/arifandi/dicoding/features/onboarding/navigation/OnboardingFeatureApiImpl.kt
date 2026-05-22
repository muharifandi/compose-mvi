/**
 * Created by Muh. Arifandi on 23/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:onboarding:impl
 * File : OnboardingFeatureApiImpl.kt
 */

package com.muh.arifandi.dicoding.features.onboarding.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.muh.arifandi.dicoding.core.architecture.navigation.FeatureApi
import com.muh.arifandi.dicoding.features.onboarding.api.OnboardingDestinations
import com.muh.arifandi.dicoding.features.onboarding.ui.OnboardingScreen
import javax.inject.Inject

class OnboardingFeatureApiImpl @Inject constructor() : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    ) {
        navGraphBuilder.composable<OnboardingDestinations> {
            OnboardingScreen(
                onNavigateToLogin = {
                    // Navigasi ke login
                }
            )
        }
    }
}
