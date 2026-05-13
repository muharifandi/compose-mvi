package com.muh.arifandi.dicoding.core.common.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationManager @Inject constructor() : Navigator {

    private val _navigationCommands = MutableSharedFlow<NavigationCommand>(extraBufferCapacity = 1)
    val navigationCommands = _navigationCommands.asSharedFlow()

    override fun navigateTo(route: Any) {
        _navigationCommands.tryEmit(NavigationCommand.NavigateTo(route))
    }

    override fun navigateBack() {
        _navigationCommands.tryEmit(NavigationCommand.NavigateBack)
    }

    override fun navigateAndPopUpTo(route: Any, popUpTo: Any, inclusive: Boolean) {
        _navigationCommands.tryEmit(NavigationCommand.NavigateAndPopUpTo(route, popUpTo, inclusive))
    }
}

sealed interface NavigationCommand {
    data class NavigateTo(val route: Any) : NavigationCommand
    data object NavigateBack : NavigationCommand
    data class NavigateAndPopUpTo(val route: Any, val popUpTo: Any, val inclusive: Boolean) : NavigationCommand
}
