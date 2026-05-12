/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:bookmark
 * File : BookmarkScreen.kt
 *
 * Description:
 * Layar yang menampilkan daftar berita yang telah disimpan oleh pengguna.
 */

package com.muh.arifandi.dicoding.features.bookmark

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.muh.arifandi.dicoding.core.ui.designsystem.AppToolbar
import com.muh.arifandi.dicoding.core.ui.designsystem.AppTextButton
import com.muh.arifandi.dicoding.core.ui.designsystem.EmptyView
import com.muh.arifandi.dicoding.core.ui.designsystem.LoadingView
import com.muh.arifandi.dicoding.features.news.ui.component.NewsItem
import com.muh.arifandi.dicoding.features.bookmark.state.BookmarkEffect
import com.muh.arifandi.dicoding.features.bookmark.state.BookmarkIntent
import com.muh.arifandi.dicoding.features.bookmark.state.BookmarkState
import com.muh.arifandi.dicoding.core.model.Article
import kotlinx.coroutines.flow.collectLatest
import com.muh.arifandi.dicoding.core.ui.R as UiR

@Composable
fun BookmarkScreen(
    navController: NavController,
    onNavigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BookmarkViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is BookmarkEffect.NavigateToDetail -> onNavigateToDetail(effect.url)
                is BookmarkEffect.NavigateBack -> navController.popBackStack()
            }
        }
    }

    BookmarkContent(
        state = state,
        onBackClick = { viewModel.processIntent(BookmarkIntent.Back) },
        onArticleClick = { viewModel.processIntent(BookmarkIntent.ClickArticle(it)) },
        onDeleteClick = { viewModel.processIntent(BookmarkIntent.DeleteFavorite(it)) },
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkContent(
    state: BookmarkState,
    onBackClick: () -> Unit,
    onArticleClick: (Article) -> Unit,
    onDeleteClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var articleToDeleteUrl by remember { mutableStateOf<String?>(null) }

    if (articleToDeleteUrl != null) {
        AlertDialog(
            onDismissRequest = { articleToDeleteUrl = null },
            title = { Text(text = stringResource(id = UiR.string.delete_favorite)) },
            text = { Text(text = stringResource(id = UiR.string.delete_favorite_desc)) },
            confirmButton = {
                AppTextButton(
                    text = stringResource(id = UiR.string.delete),
                    onClick = {
                        onDeleteClick(articleToDeleteUrl!!)
                        articleToDeleteUrl = null
                    }
                )
            },
            dismissButton = {
                AppTextButton(
                    text = stringResource(id = UiR.string.cancel),
                    onClick = { articleToDeleteUrl = null }
                )
            }
        )
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            AppToolbar(
                title = stringResource(id = UiR.string.favorites),
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = UiR.string.back)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                state.isLoading -> LoadingView(modifier = Modifier.align(Alignment.Center))
                state.favoriteArticles.isEmpty() -> EmptyView(message = stringResource(id = UiR.string.no_favorites_yet))
                else -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(
                            items = state.favoriteArticles,
                            key = { it.url }
                        ) { article ->
                            Box {
                                NewsItem(
                                    title = article.title,
                                    imageUrl = article.imageUrl,
                                    description = article.description,
                                    onClick = { onArticleClick(article) }
                                )
                                IconButton(
                                    onClick = { articleToDeleteUrl = article.url },
                                    modifier = Modifier
                                        .align(Alignment.TopEnd)
                                        .padding(top = 8.dp, end = 16.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = stringResource(id = UiR.string.favorite),
                                        tint = MaterialTheme.colorScheme.error
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
