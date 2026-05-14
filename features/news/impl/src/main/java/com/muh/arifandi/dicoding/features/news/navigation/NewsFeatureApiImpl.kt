package com.muh.arifandi.dicoding.features.news.navigation

import android.net.Uri
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.muh.arifandi.dicoding.core.architecture.navigation.FeatureApi
import com.muh.arifandi.dicoding.features.news.api.NewsDestinations
import com.muh.arifandi.dicoding.features.news.ui.bookmark.BookmarkScreen
import com.muh.arifandi.dicoding.features.news.ui.detail.DetailScreen
import com.muh.arifandi.dicoding.features.news.ui.home.HomeScreen
import javax.inject.Inject

class NewsFeatureApiImpl @Inject constructor() : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    ) {
        navGraphBuilder.composable<NewsDestinations.Home> {
            HomeScreen()
        }

        navGraphBuilder.composable<NewsDestinations.Detail> { backStackEntry ->
            val detail: NewsDestinations.Detail = backStackEntry.toRoute()
            val decodedUrl = Uri.decode(detail.url)
            DetailScreen(url = decodedUrl)
        }

        navGraphBuilder.composable<NewsDestinations.Bookmark> {
            BookmarkScreen()
        }
    }
}
