/**
 * Created by Muh. Arifandi on 25/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:master:impl
 * File : MasterFeatureApiImpl.kt
 *
 * Description:
 * Implementasi API fitur Master untuk mendaftarkan grafik navigasi ke dalam aplikasi.
 */
package com.muh.arifandi.dicoding.features.master.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.muh.arifandi.dicoding.core.architecture.navigation.FeatureApi
import com.muh.arifandi.dicoding.features.master.api.MasterDestinations
import com.muh.arifandi.dicoding.features.master.ui.MasterScreen
import javax.inject.Inject

class MasterFeatureApiImpl @Inject constructor() : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    ) {
        navGraphBuilder.composable<MasterDestinations> {
            MasterScreen()
        }
    }
}
