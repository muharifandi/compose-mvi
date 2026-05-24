package com.muh.arifandi.dicoding.features.login.ui

import com.muh.arifandi.dicoding.core.architecture.mvi.BaseViewModel
import com.muh.arifandi.dicoding.features.login.ui.state.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() :
    BaseViewModel<LoginState, LoginIntent, LoginEffect>(LoginState()) {

    override fun processIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.LoadInitialData -> { /* Logic */ }
            is LoginIntent.EmailChanged -> {
                setState { copy(email = intent.value) }
            }
            is LoginIntent.PasswordChanged -> {
                setState { copy(password = intent.value) }
            }
            is LoginIntent.Submit -> {
                // Handle Login logic
            }
            is LoginIntent.NavigateToRegister -> {
                sendEffect { LoginEffect.NavigateToRegister }
            }
            is LoginIntent.NavigateToForgotPassword -> {
                sendEffect { LoginEffect.NavigateToForgotPassword }
            }
        }
    }
}
