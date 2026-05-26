/**
 * Created by Muh. Arifandi on 25/05/26.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: LoginViewModel
 */
package com.muh.arifandi.dicoding.features.login.ui

import androidx.lifecycle.viewModelScope
import com.muh.arifandi.dicoding.core.architecture.mvi.BaseViewModel
import com.muh.arifandi.dicoding.features.login.domain.usecase.LoginUseCase
import com.muh.arifandi.dicoding.features.login.ui.state.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel<LoginState, LoginIntent, LoginEffect>(LoginState()) {

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
                login()
            }
            is LoginIntent.NavigateToRegister -> {
                sendEffect { LoginEffect.NavigateToRegister }
            }
            is LoginIntent.NavigateToForgotPassword -> {
                sendEffect { LoginEffect.NavigateToForgotPassword }
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            setState { copy(isLoading = true) }
            val result = loginUseCase(state.value.email, state.value.password)
            setState { copy(isLoading = false) }
            
            result.onSuccess {
                sendEffect { LoginEffect.NavigateToHome }
            }.onFailure {
                // Handle error
            }
        }
    }
}
