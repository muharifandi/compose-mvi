/**
 * Created by Muh. Arifandi on 25/05/26.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: SearchState
 */
package com.muh.arifandi.dicoding.features.master.ui.search.state

import androidx.compose.runtime.Immutable
import com.muh.arifandi.dicoding.core.architecture.mvi.UiState
import com.muh.arifandi.dicoding.features.master.domain.model.SearchItemModel

@Immutable
data class SearchState(
    val query: String = "",
    val isLoading: Boolean = false,
    val searchItems: List<SearchItemModel> = emptyList(),
    val searchResults: List<String> = emptyList()
) : UiState
