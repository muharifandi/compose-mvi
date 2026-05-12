package com.muh.arifandi.dicoding.core.common.di

import com.muh.arifandi.dicoding.core.common.navigation.Navigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {

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
