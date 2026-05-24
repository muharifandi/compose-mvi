/**
 * Created by Muh. Arifandi on 24/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:forgotpassword:impl
 * File : ForgotpasswordFeatureApiImpl.kt
 */
package com.muh.arifandi.dicoding.features.forgotpassword.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.muh.arifandi.dicoding.core.architecture.navigation.FeatureApi
import com.muh.arifandi.dicoding.features.forgotpassword.api.ForgotpasswordDestinations
import com.muh.arifandi.dicoding.features.forgotpassword.ui.ForgotpasswordScreen
import com.muh.arifandi.dicoding.features.forgotpassword.ui.changepassword.ChangePasswordScreen
import com.muh.arifandi.dicoding.features.forgotpassword.ui.success.ChangePasswordSuccessScreen
import javax.inject.Inject

/**
 * Implementasi navigasi untuk fitur Forgot Password.
 */
class ForgotpasswordFeatureApiImpl @Inject constructor() : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    ) {
        navGraphBuilder.composable<ForgotpasswordDestinations.Request> {
            ForgotpasswordScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToChangePassword = {
                    navController.navigate(ForgotpasswordDestinations.ChangePassword)
                }
            )
        }

        navGraphBuilder.composable<ForgotpasswordDestinations.ChangePassword> {
            ChangePasswordScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onSuccess = {
                    navController.navigate(ForgotpasswordDestinations.Success)
                }
            )
        }

        navGraphBuilder.composable<ForgotpasswordDestinations.Success> {
            ChangePasswordSuccessScreen(
                onOkClick = {
                    // Navigate to Login or main screen and clear stack
                    navController.popBackStack(
                        route = ForgotpasswordDestinations.Request,
                        inclusive = true
                    )
                }
            )
        }
    }
}
