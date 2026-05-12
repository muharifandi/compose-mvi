/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:detail
 * File : DetailContent.kt
 *
 * Description:
 * Konten utama layar detail berita yang menampilkan WebView dan integrasi favorit.
 */

package com.muh.arifandi.dicoding.features.news.ui.detail

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.viewinterop.AndroidView
import com.muh.arifandi.dicoding.core.ui.designsystem.ErrorView
import com.muh.arifandi.dicoding.core.ui.designsystem.LoadingView
import com.muh.arifandi.dicoding.features.news.ui.detail.state.DetailState
import com.muh.arifandi.dicoding.core.ui.R as UiR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailContent(
    state: DetailState,
    onBackClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier,
    onRetry: () -> Unit = {}
) {
    var showDeleteDialog by remember { mutableStateOf(false) }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text(text = stringResource(id = UiR.string.remove_favorite)) },
            text = { Text(text = stringResource(id = UiR.string.remove_favorite_desc)) },
            confirmButton = {
                TextButton(onClick = {
                    onFavoriteClick()
                    showDeleteDialog = false
                }) {
                    Text(text = stringResource(id = UiR.string.remove))
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text(text = stringResource(id = UiR.string.cancel))
                }
            }
        )
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = state.article?.title ?: stringResource(id = UiR.string.article_detail),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = UiR.string.back)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        if (state.isFavorite) {
                            showDeleteDialog = true
                        } else {
                            onFavoriteClick()
                        }
                    }) {
                        Icon(
                            imageVector = if (state.isFavorite) Icons.Default.Favorite 
                                         else Icons.Default.FavoriteBorder,
                            contentDescription = stringResource(id = UiR.string.favorite),
                            tint = if (state.isFavorite) MaterialTheme.colorScheme.primary 
                                   else MaterialTheme.colorScheme.onSurface
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
                state.isLoading -> {
                    LoadingView(modifier = Modifier.align(Alignment.Center))
                }
                state.error != null -> {
                    ErrorView(
                        message = state.error,
                        onRetry = onRetry,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                state.url.isNotBlank() -> {
                    ArticleWebView(url = state.url)
                }
                else -> {
                    ErrorView(
                        message = stringResource(id = UiR.string.no_url_provided),
                        onRetry = onRetry,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
private fun ArticleWebView(
    url: String,
    modifier: Modifier = Modifier
) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                loadUrl(url)
            }
        },
        modifier = modifier.fillMaxSize()
    )
}
