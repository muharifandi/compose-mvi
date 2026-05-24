package com.muh.arifandi.dicoding.features.forgotpassword.ui.changepassword.state

import androidx.compose.runtime.Immutable
import com.muh.arifandi.dicoding.core.architecture.mvi.UiState

@Immutable
data class ChangePasswordState(
    val newPassword: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
) : UiState {
    val isButtonEnabled: Boolean get() = newPassword.isNotBlank() && 
            confirmPassword.isNotBlank() && 
            newPassword == confirmPassword && 
            !isLoading

    val isConfirmPasswordError: Boolean get() = confirmPassword.isNotBlank() && 
            newPassword != confirmPassword
}
