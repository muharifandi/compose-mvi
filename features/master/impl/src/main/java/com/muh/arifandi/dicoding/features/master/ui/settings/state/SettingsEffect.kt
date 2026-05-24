package com.muh.arifandi.dicoding.features.master.ui.settings.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiEffect

sealed interface SettingsEffect : UiEffect {
    data object RestartApp : SettingsEffect
}
