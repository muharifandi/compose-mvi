package com.muh.arifandi.dicoding.features.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muh.arifandi.dicoding.core.common.navigation.Navigator
import com.muh.arifandi.dicoding.features.news.api.NewsDestinations
import com.muh.arifandi.dicoding.features.splash.api.SplashDestinations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

    init {
        viewModelScope.launch {
            delay(2000)
            navigator.navigateAndPopUpTo(
                route = NewsDestinations.Home,
                popUpTo = SplashDestinations,
                inclusive = true
            )
        }
    }
}
