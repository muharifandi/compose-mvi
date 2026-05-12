package com.muh.arifandi.dicoding.features.news.ui.detail.state

import com.muh.arifandi.dicoding.core.common.mvi.UiEffect

/**
 * Created by Muh. Arifandi on 07/05/26.
 * Email : arif76440@gmail.com
 * Project: My Application
 * File: DetailEffect
 */
sealed interface DetailEffect : UiEffect {
    data object NavigateBack : DetailEffect
    data class ShowToast(val message: String) : DetailEffect
}
