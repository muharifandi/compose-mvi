/**
 * Created by Muh. Arifandi on 07/05/26.
 * Email : arif76440@gmail.com
 * Project: My Application
 * File: DetailViewModel
 */
/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:detail
 * File : DetailViewModel.kt
 *
 * Description:
 * ViewModel untuk layar Detail berita yang mengelola tampilan konten web
 * dan status favorit.
 */

package com.muh.arifandi.dicoding.features.news.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.muh.arifandi.dicoding.core.architecture.mvi.BaseViewModel
import com.muh.arifandi.dicoding.core.common.navigation.Navigator
import com.muh.arifandi.dicoding.features.news.domain.repository.NewsRepository
import com.muh.arifandi.dicoding.features.news.ui.detail.state.DetailEffect
import com.muh.arifandi.dicoding.features.news.ui.detail.state.DetailIntent
import com.muh.arifandi.dicoding.features.news.ui.detail.state.DetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: NewsRepository,
    private val navigator: Navigator,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel<DetailState, DetailIntent, DetailEffect>(DetailState()) {

    private var isProcessingFavorite = false
    private var loadJob: kotlinx.coroutines.Job? = null

    init {
        savedStateHandle.get<String>("url")?.let { url ->
            if (state.value.article == null) {
                loadArticle(url)
            }
        }
    }

    override fun processIntent(intent: DetailIntent) {
        when (intent) {
            is DetailIntent.LoadArticle -> {
                if (savedStateHandle.get<String>("url") != intent.url || state.value.article == null) {
                    savedStateHandle["url"] = intent.url
                    loadArticle(intent.url)
                }
            }
            is DetailIntent.ToggleFavorite -> toggleFavorite()
            is DetailIntent.Back -> navigator.navigateBack()
        }
    }

    private fun loadArticle(url: String) {
        setState { copy(url = url, isLoading = true) }
        loadJob?.cancel()
        loadJob = viewModelScope.launch {
            combine(
                repository.getArticleByUrl(url),
                repository.isFavorite(url)
            ) { article, isFavorite ->
                article to isFavorite
            }.collect { (article, isFavorite) ->
                setState { 
                    copy(
                        article = article ?: this.article, 
                        isFavorite = isFavorite,
                        isLoading = false,
                        error = if (article == null && this.article == null && !isLoading) "Article not found" else null
                    ) 
                }
            }
        }
    }

    private fun toggleFavorite() {
        if (isProcessingFavorite) return
        val article = state.value.article ?: return
        
        viewModelScope.launch {
            isProcessingFavorite = true
            try {
                if (state.value.isFavorite) {
                    repository.deleteFavorite(article.url)
                    sendEffect { DetailEffect.ShowToast("Removed from favorites") }
                } else {
                    repository.saveFavorite(article)
                    sendEffect { DetailEffect.ShowToast("Added to favorites") }
                }
            } catch (e: Exception) {
                sendEffect { DetailEffect.ShowToast("Error: ${e.message}") }
            } finally {
                isProcessingFavorite = false
            }
        }
    }
}
