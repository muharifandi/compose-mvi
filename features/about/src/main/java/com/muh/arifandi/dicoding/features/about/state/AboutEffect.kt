package com.muh.arifandi.dicoding.features.about.state

import com.muh.arifandi.dicoding.core.common.mvi.UiEffect

sealed interface AboutEffect : UiEffect {
    data object NavigateBack : AboutEffect
}
