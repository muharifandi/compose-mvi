package com.muh.arifandi.dicoding.features.onboarding.ui.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiIntent

sealed interface OnboardingIntent : UiIntent {
    data object LoadInitialData : OnboardingIntent
}
