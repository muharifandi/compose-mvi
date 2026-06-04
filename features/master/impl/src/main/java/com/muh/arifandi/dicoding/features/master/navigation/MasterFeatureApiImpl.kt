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
import com.muh.arifandi.dicoding.features.master.api.BranchDestination
import com.muh.arifandi.dicoding.features.master.api.ExchangeCurrencyDestination
import com.muh.arifandi.dicoding.features.master.api.ExchangeRateDestination
import com.muh.arifandi.dicoding.features.master.api.InterestRateDestination
import com.muh.arifandi.dicoding.features.master.api.MasterDestinations
import com.muh.arifandi.dicoding.features.master.ui.MasterScreen
import com.muh.arifandi.dicoding.features.master.ui.branch.BranchScreen
import com.muh.arifandi.dicoding.features.master.ui.exchange.ExchangeRateScreen
import com.muh.arifandi.dicoding.features.master.ui.exchange.currency.ExchangeCurrencyScreen
import com.muh.arifandi.dicoding.features.master.ui.interest.InterestRateScreen
import com.muh.arifandi.dicoding.features.transfer.api.TransferDestination
import javax.inject.Inject

class MasterFeatureApiImpl @Inject constructor() : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    ) {
        navGraphBuilder.composable<MasterDestinations> {
            MasterScreen(
                onNavigateByRoute = { route ->
                    when (route) {
                        "branch_destination" -> navController.navigate(BranchDestination)
                        "interest_rate_destination" -> navController.navigate(InterestRateDestination)
                        "exchange_rate_destination" -> navController.navigate(ExchangeRateDestination)
                        "exchange_currency_destination" -> navController.navigate(ExchangeCurrencyDestination)
                        "transfer_destination" -> navController.navigate(TransferDestination)
                        // Tambahkan pemetaan route lain di sini
                    }
                }
            )
        }

        navGraphBuilder.composable<BranchDestination> {
            BranchScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        navGraphBuilder.composable<InterestRateDestination> {
            InterestRateScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        navGraphBuilder.composable<ExchangeRateDestination> {
            ExchangeRateScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        navGraphBuilder.composable<ExchangeCurrencyDestination> {
            ExchangeCurrencyScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
