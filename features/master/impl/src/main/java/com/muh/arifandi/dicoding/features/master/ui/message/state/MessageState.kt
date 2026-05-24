package com.muh.arifandi.dicoding.features.master.ui.message.state

import androidx.compose.runtime.Immutable
import com.muh.arifandi.dicoding.core.architecture.mvi.UiState

@Immutable
data class MessageState(
    val isLoading: Boolean = false,
    val messages: List<String> = emptyList()
) : UiState
