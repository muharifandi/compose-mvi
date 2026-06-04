package com.muh.arifandi.dicoding.features.splash.ui

import androidx.lifecycle.viewModelScope
import com.muh.arifandi.dicoding.core.architecture.mvi.BaseViewModel
import com.muh.arifandi.dicoding.features.splash.ui.state.SplashEffect
import com.muh.arifandi.dicoding.features.splash.ui.state.SplashIntent
import com.muh.arifandi.dicoding.features.splash.ui.state.SplashState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() :
    BaseViewModel<SplashState, SplashIntent, SplashEffect>(SplashState()) {

    init {
        processIntent(SplashIntent.CheckSession)
    }

    override fun processIntent(intent: SplashIntent) {
        when (intent) {
            is SplashIntent.CheckSession -> checkSession()
        }
    }

    private fun checkSession() {
        viewModelScope.launch {
            delay(2000) // Simulate splash delay
            sendEffect { SplashEffect.NavigateToLogin }
        }
    }
}
