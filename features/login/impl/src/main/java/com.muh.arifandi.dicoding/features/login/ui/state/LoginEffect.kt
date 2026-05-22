package com.muh.arifandi.dicoding.features.login.ui.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiEffect

sealed interface LoginEffect : UiEffect {
    data class ShowError(val message: String) : LoginEffect
}
