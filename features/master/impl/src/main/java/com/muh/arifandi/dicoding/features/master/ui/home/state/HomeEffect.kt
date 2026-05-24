package com.muh.arifandi.dicoding.features.master.ui.home.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiEffect

sealed interface HomeEffect : UiEffect {
    data class ShowError(val message: String) : HomeEffect
}
