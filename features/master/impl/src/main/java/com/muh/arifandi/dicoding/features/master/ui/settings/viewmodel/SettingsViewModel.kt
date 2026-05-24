package com.muh.arifandi.dicoding.features.master.ui.settings.viewmodel

import com.muh.arifandi.dicoding.core.architecture.mvi.BaseViewModel
import com.muh.arifandi.dicoding.features.master.ui.settings.state.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor() :
    BaseViewModel<SettingsState, SettingsIntent, SettingsEffect>(SettingsState()) {

    override fun processIntent(intent: SettingsIntent) {
        when (intent) {
            is SettingsIntent.ToggleDarkMode -> {
                setState { copy(isDarkMode = intent.enabled) }
            }
        }
    }
}
