/**
 * Created by Muh. Arifandi on 07/05/26.
 * Email : arif76440@gmail.com
 * Project: My Application
 * File: AppNavHost
 */
package com.muh.arifandi.dicoding.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.muh.arifandi.dicoding.features.news.ui.home.HomeScreen
import com.muh.arifandi.dicoding.features.news.ui.detail.DetailScreen
import com.muh.arifandi.dicoding.features.splash.SplashScreen
import com.muh.arifandi.dicoding.features.about.AboutScreen
import com.muh.arifandi.dicoding.features.news.ui.bookmark.BookmarkScreen
import com.muh.arifandi.dicoding.features.splash.api.SplashDestinations
import com.muh.arifandi.dicoding.features.news.api.NewsDestinations
import com.muh.arifandi.dicoding.features.about.api.AboutDestinations

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = SplashDestinations,
        modifier = modifier
    ) {
        composable<SplashDestinations> {
            SplashScreen()
        }

        composable<NewsDestinations.Home> {
            HomeScreen()
        }

        composable<NewsDestinations.Detail> { backStackEntry ->
            val detail: NewsDestinations.Detail = backStackEntry.toRoute()
            val decodedUrl = Uri.decode(detail.url)
            DetailScreen(
                url = decodedUrl
            )
        }

        composable<AboutDestinations> {
            AboutScreen()
        }

        composable<NewsDestinations.Bookmark> {
            BookmarkScreen()
        }
    }
}
