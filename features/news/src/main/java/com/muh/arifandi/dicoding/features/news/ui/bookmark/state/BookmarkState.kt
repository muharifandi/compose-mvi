package com.muh.arifandi.dicoding.features.news.ui.bookmark.state

import com.muh.arifandi.dicoding.core.common.mvi.UiState
import com.muh.arifandi.dicoding.features.news.domain.model.Article

data class BookmarkState(
    val isLoading: Boolean = false,
    val favoriteArticles: List<Article> = emptyList(),
    val error: String? = null
) : UiState
