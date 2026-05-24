package com.muh.arifandi.dicoding.features.forgotpassword.ui.changepassword.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiIntent

sealed interface ChangePasswordIntent : UiIntent {
    data class NewPasswordChanged(val password: String) : ChangePasswordIntent
    data class ConfirmPasswordChanged(val password: String) : ChangePasswordIntent
    data object Submit : ChangePasswordIntent
    data object NavigateBack : ChangePasswordIntent
}
