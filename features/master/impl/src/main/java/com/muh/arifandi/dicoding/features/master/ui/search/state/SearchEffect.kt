package com.muh.arifandi.dicoding.features.master.ui.search.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiEffect

sealed interface SearchEffect : UiEffect {
    data class ShowToast(val message: String) : SearchEffect
}
