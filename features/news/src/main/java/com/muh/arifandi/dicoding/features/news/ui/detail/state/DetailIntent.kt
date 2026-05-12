package com.muh.arifandi.dicoding.features.detail.state

import com.muh.arifandi.dicoding.core.common.mvi.UiIntent

/**
 * Created by Muh. Arifandi on 07/05/26.
 * Email : arif76440@gmail.com
 * Project: My Application
 * File: DetailIntent
 */
sealed interface DetailIntent : UiIntent {
    data class LoadArticle(val url: String) : DetailIntent
    data object ToggleFavorite : DetailIntent
    data object Back : DetailIntent
}
