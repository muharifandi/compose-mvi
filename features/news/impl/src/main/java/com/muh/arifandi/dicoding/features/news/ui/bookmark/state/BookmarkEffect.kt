package com.muh.arifandi.dicoding.features.news.ui.bookmark.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiEffect

sealed interface BookmarkEffect : UiEffect {
    data class NavigateToDetail(val url: String) : BookmarkEffect
    data object NavigateBack : BookmarkEffect
}
