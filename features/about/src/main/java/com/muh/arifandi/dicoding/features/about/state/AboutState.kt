package com.muh.arifandi.dicoding.features.about.state

import com.muh.arifandi.dicoding.core.common.mvi.UiState

data class AboutState(
    val name: String = "Muh. Arifandi",
    val email: String = "arif76440@gmail.com",
    val photoUrl: String = "https://avatars.githubusercontent.com/u/47544062?v=4" // Ganti dengan foto real jika ada
) : UiState
