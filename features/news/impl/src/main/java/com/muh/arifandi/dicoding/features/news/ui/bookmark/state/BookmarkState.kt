package com.muh.arifandi.dicoding.features.news.ui.bookmark.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiState
import com.muh.arifandi.dicoding.core.model.Article

data class BookmarkState(
    val isLoading: Boolean = false,
    val favoriteArticles: List<Article> = emptyList(),
    val error: String? = null
) : UiState
