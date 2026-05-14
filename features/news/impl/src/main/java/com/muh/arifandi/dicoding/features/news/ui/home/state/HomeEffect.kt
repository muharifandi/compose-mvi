package com.muh.arifandi.dicoding.features.news.ui.home.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiEffect

/**
 * Created by Muh. Arifandi on 07/05/26.
 * Email : arif76440@gmail.com
 * Project: My Application
 * File: HomeEffect
 */
sealed interface HomeEffect : UiEffect {
    data class NavigateToDetail(val url: String) : HomeEffect
    data class ShowError(val message: String) : HomeEffect
}
