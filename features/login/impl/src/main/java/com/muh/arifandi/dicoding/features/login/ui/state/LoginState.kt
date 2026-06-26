package com.muh.arifandi.dicoding.features.login.ui.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiState

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
) : UiState
