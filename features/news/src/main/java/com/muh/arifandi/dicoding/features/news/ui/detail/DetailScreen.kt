/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:detail
 * File : DetailScreen.kt
 *
 * Description:
 * Layar detail berita yang menampilkan WebView dan tombol favorit.
 */

package com.muh.arifandi.dicoding.features.news.ui.detail

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.muh.arifandi.dicoding.features.news.ui.detail.state.DetailEffect
import com.muh.arifandi.dicoding.features.news.ui.detail.state.DetailIntent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DetailScreen(
    url: String,
    navController: NavController,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()
    
    var lastBackClickTime by remember { mutableLongStateOf(0L) }

    LaunchedEffect(url) {
        viewModel.processIntent(DetailIntent.LoadArticle(url))
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is DetailEffect.NavigateBack -> {
                    val currentTime = System.currentTimeMillis()
                    if (currentTime - lastBackClickTime > 500L) {
                        navController.popBackStack()
                        lastBackClickTime = currentTime
                    }
                }
                is DetailEffect.ShowToast -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    DetailContent(
        state = state,
        onBackClick = { viewModel.processIntent(DetailIntent.Back) },
        onFavoriteClick = { viewModel.processIntent(DetailIntent.ToggleFavorite) },
        onRetry = { viewModel.processIntent(DetailIntent.LoadArticle(url)) }
    )
}
