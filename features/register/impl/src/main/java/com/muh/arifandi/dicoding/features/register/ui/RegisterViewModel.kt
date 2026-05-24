package com.muh.arifandi.dicoding.features.register.ui

import com.muh.arifandi.dicoding.core.architecture.mvi.BaseViewModel
import com.muh.arifandi.dicoding.features.register.ui.state.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() :
    BaseViewModel<RegisterState, RegisterIntent, RegisterEffect>(RegisterState()) {

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
        // Logic untuk hit API register
        setState { copy(isLoading = true) }
        // Simulasi sukses
        setState { copy(isLoading = false) }
        sendEffect { RegisterEffect.NavigateToLogin }
    }
}
