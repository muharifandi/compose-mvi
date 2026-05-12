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
import com.muh.arifandi.dicoding.features.home.HomeScreen
import com.muh.arifandi.dicoding.features.detail.DetailScreen
import com.muh.arifandi.dicoding.features.splash.SplashScreen
import com.muh.arifandi.dicoding.features.about.AboutScreen
import com.muh.arifandi.dicoding.features.bookmark.BookmarkScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.Splash,
        modifier = modifier
    ) {
        composable<Destinations.Splash> {
            SplashScreen(
                onNavigateToHome = {
                    navController.navigate(Destinations.Home) {
                        popUpTo(Destinations.Splash) { inclusive = true }
                    }
                }
            )
        }

        composable<Destinations.Home> {
            HomeScreen(
                onNavigateToDetail = { url ->
                    val encodedUrl = Uri.encode(url)
                    navController.navigate(Destinations.Detail(encodedUrl))
                },
                onNavigateToAbout = {
                    navController.navigate(Destinations.About)
                },
                onNavigateToBookmark = {
                    navController.navigate(Destinations.Bookmark)
                }
            )
        }

        composable<Destinations.Detail> { backStackEntry ->
            val detail: Destinations.Detail = backStackEntry.toRoute()
            val decodedUrl = Uri.decode(detail.url)
            DetailScreen(
                url = decodedUrl,
                navController = navController
            )
        }

        composable<Destinations.About> {
            AboutScreen(navController)
        }

        composable<Destinations.Bookmark> {
            BookmarkScreen(
                navController = navController,
                onNavigateToDetail = { url ->
                    val encodedUrl = Uri.encode(url)
                    navController.navigate(Destinations.Detail(encodedUrl))
                }
            )
        }
    }
}
