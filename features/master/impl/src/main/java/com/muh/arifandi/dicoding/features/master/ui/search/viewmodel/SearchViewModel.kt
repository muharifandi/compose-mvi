package com.muh.arifandi.dicoding.features.master.ui.search.viewmodel

import com.muh.arifandi.dicoding.core.architecture.mvi.BaseViewModel
import com.muh.arifandi.dicoding.features.master.ui.search.state.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() :
    BaseViewModel<SearchState, SearchIntent, SearchEffect>(SearchState()) {

    override fun processIntent(intent: SearchIntent) {
        when (intent) {
            is SearchIntent.UpdateQuery -> {
                setState { copy(query = intent.query) }
            }
            is SearchIntent.ClearSearch -> {
                setState { copy(query = "", searchResults = emptyList()) }
            }
        }
    }
}
