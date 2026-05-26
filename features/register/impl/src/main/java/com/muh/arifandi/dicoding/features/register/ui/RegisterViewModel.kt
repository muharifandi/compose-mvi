/**
 * Created by Muh. Arifandi on 25/05/26.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: RegisterViewModel
 */
package com.muh.arifandi.dicoding.features.register.ui

import androidx.lifecycle.viewModelScope
import com.muh.arifandi.dicoding.core.architecture.mvi.BaseViewModel
import com.muh.arifandi.dicoding.features.register.domain.usecase.RegisterUseCase
import com.muh.arifandi.dicoding.features.register.ui.state.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : BaseViewModel<RegisterState, RegisterIntent, RegisterEffect>(RegisterState()) {

    override fun processIntent(intent: RegisterIntent) {
        when (intent) {
            is RegisterIntent.NameChanged -> setState { copy(name = intent.value) }
            is RegisterIntent.EmailChanged -> setState { copy(email = intent.value) }
            is RegisterIntent.PasswordChanged -> setState { copy(password = intent.value) }
            is RegisterIntent.AgreementChanged -> setState { copy(isAgreed = intent.value) }
            is RegisterIntent.Submit -> handleRegistration()
            is RegisterIntent.NavigateToLogin -> sendEffect { RegisterEffect.NavigateToLogin }
        }
    }

    private fun handleRegistration() {
        viewModelScope.launch {
            setState { copy(isLoading = true) }
            val result = registerUseCase(
                state.value.name,
                state.value.email,
                state.value.password
            )
            setState { copy(isLoading = false) }
            
            result.onSuccess {
                sendEffect { RegisterEffect.NavigateToLogin }
            }
        }
    }
}
