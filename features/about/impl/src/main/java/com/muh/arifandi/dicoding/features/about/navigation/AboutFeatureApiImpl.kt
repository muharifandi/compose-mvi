package com.muh.arifandi.dicoding.features.about.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.muh.arifandi.dicoding.core.architecture.navigation.FeatureApi
import com.muh.arifandi.dicoding.features.about.AboutScreen
import com.muh.arifandi.dicoding.features.about.api.AboutDestinations
import javax.inject.Inject

class AboutFeatureApiImpl @Inject constructor() : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    ) {
        navGraphBuilder.composable<AboutDestinations> {
            AboutScreen()
        }
    }
}
