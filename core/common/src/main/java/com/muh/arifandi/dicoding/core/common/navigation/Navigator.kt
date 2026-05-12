package com.muh.arifandi.dicoding.core.common.navigation

/**
 * Created by Foundation Team
 * Navigation Bridge Pattern: Interface to decouple feature modules from NavController and specific routes.
 */
interface Navigator {
    fun navigateTo(route: Any)
    fun navigateBack()
    fun navigateAndPopUpTo(route: Any, popUpTo: Any, inclusive: Boolean = true)
}
