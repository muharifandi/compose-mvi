package com.muh.arifandi.dicoding.features.onboarding.ui.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiState
import androidx.compose.runtime.Immutable
import com.muh.arifandi.dicoding.features.onboarding.domain.model.OnboardingPage

@Immutable
data class OnboardingState(
    val items: List<OnboardingPage> = emptyList(),
    val currentPage: Int = 0,
    val isLastPage: Boolean = false,
    val isLoading: Boolean = false
) : UiState
