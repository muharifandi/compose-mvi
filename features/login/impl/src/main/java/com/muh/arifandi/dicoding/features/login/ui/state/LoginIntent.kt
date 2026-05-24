package com.muh.arifandi.dicoding.features.login.ui.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiIntent

sealed interface LoginIntent : UiIntent {
    data object LoadInitialData : LoginIntent
}
