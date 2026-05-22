package com.muh.arifandi.dicoding.features.onboarding.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaLoadingView

@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    if (state.isLoading) {
        SakaLoadingView()
    } else {
        // Build your UI here
    }
}
