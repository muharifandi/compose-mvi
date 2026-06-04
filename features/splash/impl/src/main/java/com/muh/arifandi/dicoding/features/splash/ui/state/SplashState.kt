package com.muh.arifandi.dicoding.features.splash.ui.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiState
import javax.annotation.concurrent.Immutable

@Immutable
data class SplashState(
    val isLoading: Boolean = true
) : UiState
