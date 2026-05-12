/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:home
 * File : HomeScreen.kt
 *
 * Description:
 * Layar utama aplikasi yang menampilkan daftar berita (headlines) dan fitur pencarian.
 */
package com.muh.arifandi.dicoding.features.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.muh.arifandi.dicoding.core.ui.designsystem.AppToolbar
import com.muh.arifandi.dicoding.core.ui.designsystem.ErrorView
import com.muh.arifandi.dicoding.core.ui.designsystem.LoadingView
import com.muh.arifandi.dicoding.features.news.ui.component.NewsItem
import com.muh.arifandi.dicoding.core.ui.designsystem.SearchBar
import com.muh.arifandi.dicoding.domain.news.model.Article
import com.muh.arifandi.dicoding.features.home.state.HomeEffect
import com.muh.arifandi.dicoding.features.home.state.HomeIntent
import com.muh.arifandi.dicoding.core.ui.R as UiR
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(
    onNavigateToDetail: (String) -> Unit,
    onNavigateToAbout: () -> Unit,
    onNavigateToBookmark: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val pagedArticles = viewModel.pagedArticles.collectAsLazyPagingItems()
    
    var lastClickTime by remember { mutableLongStateOf(0L) }

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is HomeEffect.NavigateToDetail -> {
                    val currentTime = System.currentTimeMillis()
                    if (currentTime - lastClickTime > 500L) {
                        onNavigateToDetail(effect.url)
                        lastClickTime = currentTime
                    }
                }
                is HomeEffect.ShowError -> { }
            }
        }
    }

    Scaffold(
        topBar = {
            Column {
                AppToolbar(
                    title = stringResource(id = UiR.string.news_app),
                    actions = {
                        IconButton(
                            onClick = onNavigateToBookmark,
                            modifier = Modifier.testTag("bookmark_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "bookmarks"
                            )
                        }
                        IconButton(
                            onClick = onNavigateToAbout,
                            modifier = Modifier.testTag("about_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = "about_page"
                            )
                        }
                    }
                )
                SearchBar(
                    onSearch = { viewModel.processIntent(HomeIntent.SearchArticle(it)) }
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (state.isPaging) {
                when (pagedArticles.loadState.refresh) {
                    is LoadState.Loading -> LoadingView(modifier = Modifier.align(Alignment.Center).testTag("loading_view"))
                    is LoadState.Error -> {
                        val e = pagedArticles.loadState.refresh as LoadState.Error
                        ErrorView(
                            message = e.error.message ?: "Unknown Error",
                            onRetry = { pagedArticles.retry() },
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    else -> {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            items(
                                count = pagedArticles.itemCount,
                                key = { index -> pagedArticles[index]?.url ?: index },
                                contentType = { "article_item" }
                            ) { index ->
                                val article = pagedArticles[index]
                                if (article != null) {
                                    NewsItem(
                                        title = article.title,
                                        imageUrl = article.imageUrl,
                                        description = article.description,
                                        onClick = { viewModel.processIntent(HomeIntent.ClickArticle(article)) }
                                    )
                                }
                            }
                        }
                    }
                }
            } else {
                when {
                    state.isLoading -> LoadingView(modifier = Modifier.align(Alignment.Center).testTag("loading_view"))
                    state.error != null -> ErrorView(
                        message = state.error!!,
                        onRetry = { viewModel.processIntent(HomeIntent.Refresh) },
                        modifier = Modifier.align(Alignment.Center)
                    )
                    else -> {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            items(
                                items = state.filteredArticles,
                                key = { article: Article -> article.url },
                                contentType = { "article_item" }
                            ) { article ->
                                NewsItem(
                                    title = article.title,
                                    imageUrl = article.imageUrl,
                                    description = article.description,
                                    onClick = { viewModel.processIntent(HomeIntent.ClickArticle(article)) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
