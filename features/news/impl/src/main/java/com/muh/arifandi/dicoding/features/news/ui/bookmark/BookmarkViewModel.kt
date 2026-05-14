/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:bookmark
 * File : BookmarkViewModel.kt
 *
 * Description:
 * ViewModel untuk layar Bookmark yang mengelola state daftar berita favorit.
 */

package com.muh.arifandi.dicoding.features.news.ui.bookmark

import androidx.lifecycle.viewModelScope
import com.muh.arifandi.dicoding.core.architecture.mvi.BaseViewModel
import com.muh.arifandi.dicoding.core.common.navigation.Navigator
import com.muh.arifandi.dicoding.features.news.api.NewsDestinations
import com.muh.arifandi.dicoding.features.news.domain.repository.NewsRepository
import com.muh.arifandi.dicoding.features.news.ui.bookmark.state.BookmarkEffect
import com.muh.arifandi.dicoding.features.news.ui.bookmark.state.BookmarkIntent
import com.muh.arifandi.dicoding.features.news.ui.bookmark.state.BookmarkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val repository: NewsRepository,
    private val navigator: Navigator
) : BaseViewModel<BookmarkState, BookmarkIntent, BookmarkEffect>(BookmarkState()) {

    init {
        processIntent(BookmarkIntent.LoadFavorites)
    }

    override fun processIntent(intent: BookmarkIntent) {
        when (intent) {
            is BookmarkIntent.LoadFavorites -> loadFavorites()
            is BookmarkIntent.ClickArticle -> {
                navigator.navigateTo(NewsDestinations.Detail(intent.article.url))
            }
            is BookmarkIntent.DeleteFavorite -> deleteFavorite(intent.url)
            is BookmarkIntent.Back -> navigator.navigateBack()
        }
    }

    private fun deleteFavorite(url: String) {
        viewModelScope.launch {
            repository.deleteFavorite(url)
        }
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            setState { copy(isLoading = true) }
            repository.getAllFavorites().collectLatest { articles ->
                setState { 
                    copy(
                        isLoading = false,
                        favoriteArticles = articles,
                        error = null
                    ) 
                }
            }
        }
    }
}
