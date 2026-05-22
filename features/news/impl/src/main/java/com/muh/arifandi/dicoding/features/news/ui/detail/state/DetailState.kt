package com.muh.arifandi.dicoding.features.news.ui.detail.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiState
import com.muh.arifandi.dicoding.core.model.Article

/**
 * Created by Muh. Arifandi on 07/05/26.
 * Email : arif76440@gmail.com
 * Project: My Application
 * File: DetailState
 */
data class DetailState(
    val url: String = "",
    val isLoading: Boolean = false,
    val article: Article? = null,
    val isFavorite: Boolean = false,
    val error: String? = null
) : UiState
