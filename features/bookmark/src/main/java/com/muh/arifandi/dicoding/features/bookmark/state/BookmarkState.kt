package com.muh.arifandi.dicoding.features.bookmark.state

import com.muh.arifandi.dicoding.core.common.mvi.UiState
import com.muh.arifandi.dicoding.domain.news.model.Article

data class BookmarkState(
    val isLoading: Boolean = false,
    val favoriteArticles: List<Article> = emptyList(),
    val error: String? = null
) : UiState
