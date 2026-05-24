package com.muh.arifandi.dicoding.features.master.ui.search.state

import androidx.compose.runtime.Immutable
import com.muh.arifandi.dicoding.core.architecture.mvi.UiState

@Immutable
data class SearchState(
    val query: String = "",
    val isLoading: Boolean = false,
    val searchResults: List<String> = emptyList()
) : UiState
