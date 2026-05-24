package com.muh.arifandi.dicoding.features.register.ui.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiIntent

sealed interface RegisterIntent : UiIntent {
    data object LoadInitialData : RegisterIntent
}
