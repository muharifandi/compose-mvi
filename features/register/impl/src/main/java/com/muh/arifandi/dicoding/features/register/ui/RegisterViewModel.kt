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
            is RegisterIntent.LoadInitialData -> { /* Logic */ }
        }
    }
}
