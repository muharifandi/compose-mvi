package com.muh.arifandi.dicoding.features.news.api

import kotlinx.serialization.Serializable

@Serializable
sealed interface NewsDestinations {
    @Serializable
    data object Home : NewsDestinations

    @Serializable
    data class Detail(val url: String) : NewsDestinations

    @Serializable
    data object Bookmark : NewsDestinations
}
