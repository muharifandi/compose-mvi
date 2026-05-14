package com.muh.arifandi.dicoding.features.news.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class Source(
    val id: String,
    val name: String,
    val description: String,
    val url: String,
    val category: String,
    val language: String,
    val country: String
)
