package com.muh.arifandi.dicoding.features.onboarding.ui.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiIntent

sealed interface OnboardingIntent : UiIntent {
    data object NextPage : OnboardingIntent
    data object PreviousPage : OnboardingIntent
    data object GetStarted : OnboardingIntent
    data object LoadPages : OnboardingIntent
}
