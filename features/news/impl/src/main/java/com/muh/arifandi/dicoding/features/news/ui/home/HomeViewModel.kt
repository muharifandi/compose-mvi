package com.muh.arifandi.dicoding.features.news.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.muh.arifandi.dicoding.core.model.ResultState
import com.muh.arifandi.dicoding.core.architecture.mvi.BaseViewModel
import com.muh.arifandi.dicoding.core.common.navigation.Navigator
import com.muh.arifandi.dicoding.features.about.api.AboutDestinations
import com.muh.arifandi.dicoding.features.news.api.NewsDestinations
import com.muh.arifandi.dicoding.features.news.data.repository.NewsPagingRepository
import com.muh.arifandi.dicoding.core.model.Article
import com.muh.arifandi.dicoding.features.news.domain.usecase.SearchNewsUseCase
import com.muh.arifandi.dicoding.features.news.ui.home.state.HomeEffect
import com.muh.arifandi.dicoding.features.news.ui.home.state.HomeIntent
import com.muh.arifandi.dicoding.features.news.ui.home.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchNewsUseCase: SearchNewsUseCase,
    private val pagingRepository: NewsPagingRepository,
    private val navigator: Navigator,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel<HomeState, HomeIntent, HomeEffect>(HomeState()) {

    private val _pagedArticles = MutableStateFlow<PagingData<Article>>(PagingData.empty())
    val pagedArticles: StateFlow<PagingData<Article>> = _pagedArticles.asStateFlow()

    companion object {
        private const val KEY_QUERY = "query"
        private const val KEY_CATEGORY = "category"
    }

    init {
        val savedQuery = savedStateHandle.get<String>(KEY_QUERY)
        val savedCategory = savedStateHandle.get<String>(KEY_CATEGORY)
        
        if (!savedQuery.isNullOrBlank()) {
            searchArticles(savedQuery)
        } else {
            loadPagedArticles(savedCategory)
        }
    }

    override fun processIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.SearchArticle -> {
                savedStateHandle[KEY_QUERY] = intent.query
                searchArticles(intent.query)
            }
            is HomeIntent.FilterCategory -> {
                savedStateHandle[KEY_CATEGORY] = intent.category
                savedStateHandle[KEY_QUERY] = ""
                loadPagedArticles(intent.category)
            }
            is HomeIntent.ClickArticle -> {
                navigator.navigateTo(NewsDestinations.Detail(intent.article.url))
            }
            is HomeIntent.ClickAbout -> navigator.navigateTo(AboutDestinations)
            is HomeIntent.ClickBookmark -> navigator.navigateTo(NewsDestinations.Bookmark)
            is HomeIntent.Refresh -> {
                val currentQuery = savedStateHandle.get<String>(KEY_QUERY)
                if (!currentQuery.isNullOrBlank()) searchArticles(currentQuery)
                else loadPagedArticles(savedStateHandle.get<String>(KEY_CATEGORY))
            }
        }
    }

    private fun loadPagedArticles(category: String?) {
        setState { copy(selectedCategory = category, isLoading = true, error = null, isPaging = true) }
        viewModelScope.launch {
            pagingRepository.getPagedTopHeadlines(category)
                .cachedIn(this)
                .collectLatest { 
                    _pagedArticles.value = it
                    setState { copy(isLoading = false) }
                }
        }
    }

    private fun searchArticles(query: String) {
        if (query.isBlank()) {
            loadPagedArticles(savedStateHandle.get<String>(KEY_CATEGORY))
            return
        }

        viewModelScope.launch {
            searchNewsUseCase(query, 1).collectLatest { result ->
                when (result) {
                    is ResultState.Loading -> setState { copy(isLoading = true, error = null, isPaging = false) }
                    is ResultState.Success -> {
                        setState { 
                            copy(
                                isLoading = false, 
                                filteredArticles = result.data,
                                error = null
                            ) 
                        }
                    }
                    is ResultState.Error -> {
                        setState { copy(isLoading = false, error = result.message) }
                        sendEffect { HomeEffect.ShowError(result.message) }
                    }
                }
            }
        }
    }
}
