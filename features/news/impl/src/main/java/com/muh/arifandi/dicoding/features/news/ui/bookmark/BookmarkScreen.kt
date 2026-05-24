/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:news:impl
 * File : BookmarkScreen.kt
 *
 * Description:
 * Layar bookmark yang menampilkan daftar berita yang telah disimpan oleh pengguna.
 */

package com.muh.arifandi.dicoding.features.news.ui.bookmark

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaTopAppBar
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaButton
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaButtonType
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaEmptyView
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaLoadingView
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaNewsCard
import com.muh.arifandi.dicoding.features.news.ui.bookmark.state.BookmarkIntent
import com.muh.arifandi.dicoding.features.news.ui.bookmark.state.BookmarkState
import com.muh.arifandi.dicoding.core.model.Article
import com.muh.arifandi.dicoding.core.ui.R as UiR

@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    viewModel: BookmarkViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

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
                SakaButton(
                    text = stringResource(id = UiR.string.delete),
                    type = SakaButtonType.LINK,
                    onClick = {
                        onDeleteClick(articleToDeleteUrl!!)
                        articleToDeleteUrl = null
                    }
                )
            },
            dismissButton = {
                SakaButton(
                    text = stringResource(id = UiR.string.cancel),
                    type = SakaButtonType.LINK,
                    onClick = { articleToDeleteUrl = null }
                )
            }
        )
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            SakaTopAppBar(
                title = stringResource(id = UiR.string.favorites),
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                state.isLoading -> SakaLoadingView(modifier = Modifier.align(Alignment.Center))
                state.favoriteArticles.isEmpty() -> SakaEmptyView(message = stringResource(id = UiR.string.no_favorites_yet))
                else -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(
                            items = state.favoriteArticles,
                            key = { it.url },
                            contentType = { "favorite_article" }
                        ) { article ->
                            val onClick = remember(article) {
                                { onArticleClick(article) }
                            }
                            Box {
                                SakaNewsCard(
                                    title = article.title ?: "",
                                    imageUrl = article.urlToImage ?: "",
                                    description = article.description ?: "",
                                    onClick = onClick
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
