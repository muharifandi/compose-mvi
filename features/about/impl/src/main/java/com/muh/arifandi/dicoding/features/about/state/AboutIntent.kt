package com.muh.arifandi.dicoding.features.about.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiIntent

sealed interface AboutIntent : UiIntent {
    data object Back : AboutIntent
}
