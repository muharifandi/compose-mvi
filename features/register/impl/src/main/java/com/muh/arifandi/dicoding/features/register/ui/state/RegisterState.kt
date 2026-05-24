package com.muh.arifandi.dicoding.features.register.ui.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiState
import androidx.compose.runtime.Immutable

@Immutable
data class RegisterState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val isAgreed: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
) : UiState
