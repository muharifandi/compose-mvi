package com.muh.arifandi.dicoding.features.about.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiEffect

sealed interface AboutEffect : UiEffect {
    data object NavigateBack : AboutEffect
}
