package com.muh.arifandi.dicoding.features.onboarding.ui.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiState
import androidx.compose.runtime.Immutable

@Immutable
data class OnboardingState(
    val isLoading: Boolean = false,
    val data: String? = null
) : UiState
