package com.muh.arifandi.dicoding.features.master.ui.message.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiEffect

sealed interface MessageEffect : UiEffect {
    data object ScrollToTop : MessageEffect
}
