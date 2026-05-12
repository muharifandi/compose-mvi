package com.muh.arifandi.dicoding.features.bookmark.state

import com.muh.arifandi.dicoding.core.common.mvi.UiIntent
import com.muh.arifandi.dicoding.domain.news.model.Article

sealed interface BookmarkIntent : UiIntent {
    data object LoadFavorites : BookmarkIntent
    data class ClickArticle(val article: Article) : BookmarkIntent
    data class DeleteFavorite(val url: String) : BookmarkIntent
    data object Back : BookmarkIntent
}
