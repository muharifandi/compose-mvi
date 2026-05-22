package com.muh.arifandi.dicoding.features.register.ui.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiEffect

sealed interface RegisterEffect : UiEffect {
    data class ShowError(val message: String) : RegisterEffect
}
