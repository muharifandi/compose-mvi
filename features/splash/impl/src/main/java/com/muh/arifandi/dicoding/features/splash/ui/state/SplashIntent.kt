package com.muh.arifandi.dicoding.features.splash.ui.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiIntent

sealed interface SplashIntent : UiIntent {
    data object CheckSession : SplashIntent
}
