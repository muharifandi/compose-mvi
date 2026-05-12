package com.muh.arifandi.dicoding.core.common.di

import com.muh.arifandi.dicoding.core.common.navigation.Navigator
import com.muh.arifandi.dicoding.core.common.util.ConnectivityObserver
import com.muh.arifandi.dicoding.core.common.util.NetworkConnectivityObserver
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CommonModule {

    @Binds
    @Singleton
    abstract fun bindConnectivityObserver(
        networkConnectivityObserver: NetworkConnectivityObserver
    ): ConnectivityObserver

    companion object {
        @Provides
        @Singleton
        fun provideNavigator(): Navigator {
            return object : Navigator {
                override fun navigateTo(route: Any) { /* Will be handled by actual NavController */ }
                override fun navigateBack() { }
                override fun navigateAndPopUpTo(route: Any, popUpTo: Any, inclusive: Boolean) { }
            }
        }
    }
}
