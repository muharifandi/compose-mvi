package com.muh.arifandi.dicoding.features.news.ui.bookmark.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiIntent
import com.muh.arifandi.dicoding.features.news.domain.model.Article

sealed interface BookmarkIntent : UiIntent {
    data object LoadFavorites : BookmarkIntent
    data class ClickArticle(val article: Article) : BookmarkIntent
    data class DeleteFavorite(val url: String) : BookmarkIntent
    data object Back : BookmarkIntent
}
