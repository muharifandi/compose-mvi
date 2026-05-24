package com.muh.arifandi.dicoding.features.master.ui.search.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiIntent

sealed interface SearchIntent : UiIntent {
    data class UpdateQuery(val query: String) : SearchIntent
    data object ClearSearch : SearchIntent
}
