package com.muh.arifandi.dicoding.core.common.di

import com.muh.arifandi.dicoding.core.common.navigation.NavigationManager
import com.muh.arifandi.dicoding.core.common.navigation.Navigator
import com.muh.arifandi.dicoding.core.common.util.ConnectivityObserver
import com.muh.arifandi.dicoding.core.common.util.NetworkConnectivityObserver
import dagger.Binds
import dagger.Module
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

    @Binds
    @Singleton
    abstract fun bindNavigator(
        navigationManager: NavigationManager
    ): Navigator
}
