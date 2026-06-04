package com.muh.arifandi.dicoding.features.master.ui.home.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiIntent

sealed interface HomeIntent : UiIntent {
    data object LoadHomeData : HomeIntent
    data class SelectCard(val index: Int) : HomeIntent
    data object ToggleDataVisibility : HomeIntent
    data object NavigateToTransfer : HomeIntent
}
