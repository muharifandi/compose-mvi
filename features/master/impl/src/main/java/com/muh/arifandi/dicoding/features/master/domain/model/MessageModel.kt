package com.muh.arifandi.dicoding.features.master.domain.model

import androidx.compose.ui.graphics.Color

data class MessageModel(
    val id: String,
    val sender: String,
    val summary: String,
    val date: String,
    val iconBackgroundColor: Color
)
