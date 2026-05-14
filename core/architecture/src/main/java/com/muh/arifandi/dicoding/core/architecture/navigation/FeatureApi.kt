package com.muh.arifandi.dicoding.core.architecture.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

/**
 * Interface to be implemented by each feature module to register its own navigation routes.
 * This enables distributed navigation and prevents the :app module from being a bottleneck.
 */
interface FeatureApi {
    fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    )
}
