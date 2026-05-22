package com.muh.arifandi.dicoding.features.onboarding.ui.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiEffect

sealed interface OnboardingEffect : UiEffect {
    data class ShowError(val message: String) : OnboardingEffect
}
