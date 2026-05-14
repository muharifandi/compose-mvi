/**
 * Created by Muh. Arifandi on 07/05/26.
 * Email : arif76440@gmail.com
 * Project: My Application
 * File: AppNavHost
 */
package com.muh.arifandi.dicoding.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.muh.arifandi.dicoding.core.architecture.navigation.FeatureApi
import com.muh.arifandi.dicoding.features.news.api.NewsDestinations

@Composable
fun AppNavHost(
    navController: NavHostController,
    featureApis: Set<FeatureApi>,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = NewsDestinations.Home,
        modifier = modifier
    ) {
        featureApis.forEach { api ->
            api.registerGraph(this, navController)
        }
    }
}
