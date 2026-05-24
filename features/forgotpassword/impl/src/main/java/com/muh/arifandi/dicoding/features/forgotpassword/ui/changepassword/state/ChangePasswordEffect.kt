package com.muh.arifandi.dicoding.features.forgotpassword.ui.changepassword.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiEffect

sealed interface ChangePasswordEffect : UiEffect {
    data object NavigateBack : ChangePasswordEffect
    data object Success : ChangePasswordEffect
    data class ShowError(val message: String) : ChangePasswordEffect
}
