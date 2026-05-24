package com.muh.arifandi.dicoding.features.login.ui.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiIntent

sealed interface LoginIntent : UiIntent {
    data object LoadInitialData : LoginIntent
    data class EmailChanged(val value: String) : LoginIntent
    data class PasswordChanged(val value: String) : LoginIntent
    data object Submit : LoginIntent
    data object NavigateToRegister : LoginIntent
    data object NavigateToForgotPassword : LoginIntent
}
