package com.muh.arifandi.dicoding.features.home.state

import com.muh.arifandi.dicoding.core.common.mvi.UiState
import com.muh.arifandi.dicoding.domain.news.model.Article

/**
 * Created by Muh. Arifandi on 07/05/26.
 * Email : arif76440@gmail.com
 * Project: My Application
 * File: HomeState
 */
data class HomeState(
    val isLoading: Boolean = false,
    val filteredArticles: List<Article> = emptyList(),
    val error: String? = null,
    val selectedCategory: String? = null,
    val isPaging: Boolean = true // QA: Flag to switch between Paged and Search results
) : UiState
