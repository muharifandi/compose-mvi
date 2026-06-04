/**
 * Created by Muh. Arifandi on 25/05/26.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: SearchViewModel
 */
package com.muh.arifandi.dicoding.features.master.ui.search

import com.muh.arifandi.dicoding.core.architecture.mvi.BaseViewModel
import com.muh.arifandi.dicoding.core.ui.R
import com.muh.arifandi.dicoding.features.master.domain.model.SearchItemModel
import com.muh.arifandi.dicoding.features.master.ui.search.state.SearchEffect
import com.muh.arifandi.dicoding.features.master.ui.search.state.SearchIntent
import com.muh.arifandi.dicoding.features.master.ui.search.state.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() :
    BaseViewModel<SearchState, SearchIntent, SearchEffect>(SearchState()) {

    init {
        loadSearchItems()
    }

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

    private fun loadSearchItems() {
        val items = listOf(
            SearchItemModel("Branch", "Search for branch", R.drawable.ic_illustration_login, "branch_destination"),
            SearchItemModel("Interest rate", "Search for interest rate", R.drawable.ic_illustration_login, "interest_rate_destination"),
            SearchItemModel("Exchange rate", "Search for exchange rate", R.drawable.ic_illustration_login, "exchange_rate_destination"),
            SearchItemModel("Exchange", "Exchange amount of money", R.drawable.ic_illustration_login, "exchange_currency_destination")
        )
        setState { copy(searchItems = items) }
    }
}
