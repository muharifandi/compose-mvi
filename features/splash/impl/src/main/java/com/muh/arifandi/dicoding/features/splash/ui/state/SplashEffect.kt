package com.muh.arifandi.dicoding.features.splash.ui.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiEffect

sealed interface SplashEffect : UiEffect {
    data object NavigateToLogin : SplashEffect
    data object NavigateToHome : SplashEffect
    data object NavigateToIntro : SplashEffect
}
