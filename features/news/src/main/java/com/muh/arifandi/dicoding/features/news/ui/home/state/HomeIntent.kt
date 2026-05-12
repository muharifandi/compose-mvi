package com.muh.arifandi.dicoding.features.news.ui.home.state

import com.muh.arifandi.dicoding.core.common.mvi.UiIntent
import com.muh.arifandi.dicoding.features.news.domain.model.Article

/**
 * Created by Muh. Arifandi on 07/05/26.
 * Email : arif76440@gmail.com
 * Project: My Application
 * File: HomeIntent
 */
sealed interface HomeIntent : UiIntent {
    data class SearchArticle(val query: String) : HomeIntent
    data class FilterCategory(val category: String?) : HomeIntent
    data class ClickArticle(val article: Article) : HomeIntent
    data object Refresh : HomeIntent
}
