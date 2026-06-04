package com.muh.arifandi.dicoding.features.master.ui.message.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiIntent

sealed interface MessageIntent : UiIntent {
    data object LoadMessages : MessageIntent
}
