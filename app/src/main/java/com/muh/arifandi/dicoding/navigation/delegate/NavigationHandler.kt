/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : app
 * File : NavigationHandler.kt
 *
 * Description:
 * Delegate untuk menangani logika eksekusi navigasi antar layar menggunakan NavController.
 */

package com.muh.arifandi.dicoding.navigation.delegate

import androidx.navigation.NavController
import com.muh.arifandi.dicoding.core.common.navigation.NavigationCommand
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Foundation Team
 * Delegate untuk menangani logika eksekusi navigasi.
 * Memisahkan tanggung jawab 'kapan' navigasi terjadi (MainActivity) 
 * dengan 'bagaimana' navigasi dieksekusi.
 */
@Singleton
class NavigationHandler @Inject constructor() {

    fun handle(command: NavigationCommand, navController: NavController) {
        when (command) {
            is NavigationCommand.NavigateTo -> {
                navController.navigate(command.route)
            }
            is NavigationCommand.NavigateBack -> {
                navController.popBackStack()
            }
            is NavigationCommand.NavigateAndPopUpTo -> {
                navController.navigate(command.route) {
                    popUpTo(command.popUpTo) { inclusive = command.inclusive }
                }
            }
        }
    }
}
