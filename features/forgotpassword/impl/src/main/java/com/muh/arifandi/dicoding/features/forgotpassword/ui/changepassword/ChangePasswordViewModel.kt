package com.muh.arifandi.dicoding.features.forgotpassword.ui.changepassword

import androidx.lifecycle.viewModelScope
import com.muh.arifandi.dicoding.core.architecture.mvi.BaseViewModel
import com.muh.arifandi.dicoding.features.forgotpassword.ui.changepassword.state.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor() :
    BaseViewModel<ChangePasswordState, ChangePasswordIntent, ChangePasswordEffect>(ChangePasswordState()) {

    override fun processIntent(intent: ChangePasswordIntent) {
        when (intent) {
            is ChangePasswordIntent.NewPasswordChanged -> {
                setState { copy(newPassword = intent.password) }
            }
            is ChangePasswordIntent.ConfirmPasswordChanged -> {
                setState { copy(confirmPassword = intent.password) }
            }
            is ChangePasswordIntent.Submit -> {
                submitChange()
            }
            is ChangePasswordIntent.NavigateBack -> {
                sendEffect { ChangePasswordEffect.NavigateBack }
            }
        }
    }

    private fun submitChange() {
        viewModelScope.launch {
            setState { copy(isLoading = true) }
            // Simulate API call
            delay(1500)
            setState { copy(isLoading = false) }
            sendEffect { ChangePasswordEffect.Success }
        }
    }
}
