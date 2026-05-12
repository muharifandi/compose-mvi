package com.muh.arifandi.dicoding.navigation

import kotlinx.serialization.Serializable

/**
 * Created by Muh. Arifandi on 07/05/26.
 * Email : arif76440@gmail.com
 * Project: My Application
 * File: Destinations
 */

@Serializable
sealed interface Destinations {

    @Serializable
    data object Splash : Destinations

    @Serializable
    data object Home : Destinations

    @Serializable
    data class Detail(val url: String) : Destinations

    @Serializable
    data object About : Destinations

    @Serializable
    data object Bookmark : Destinations
}
