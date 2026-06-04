package com.muh.arifandi.dicoding.features.transfer.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.muh.arifandi.dicoding.core.architecture.navigation.FeatureApi
import com.muh.arifandi.dicoding.features.transfer.api.TransferConfirmDestination
import com.muh.arifandi.dicoding.features.transfer.api.TransferDestination
import com.muh.arifandi.dicoding.features.transfer.ui.TransferScreen
import com.muh.arifandi.dicoding.features.transfer.ui.confirm.TransferConfirmScreen
import javax.inject.Inject

class TransferFeatureApiImpl @Inject constructor() : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    ) {
        navGraphBuilder.composable<TransferDestination> {
            TransferScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onNavigateToConfirm = { destination ->
                    navController.navigate(destination)
                }
            )
        }

        navGraphBuilder.composable<TransferConfirmDestination> {
            TransferConfirmScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onNavigateToHome = {
                    // Navigate to home/success state
                    navController.popBackStack(TransferDestination, true)
                }
            )
        }
    }
}
