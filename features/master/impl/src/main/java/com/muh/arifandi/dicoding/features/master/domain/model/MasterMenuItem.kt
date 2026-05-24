/**
 * Created by Muh. Arifandi on 25/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:master:impl
 * File : MasterMenuItem.kt
 *
 * Description:
 * Model data untuk item menu yang ditampilkan di grid dashboard Master.
 */

package com.muh.arifandi.dicoding.features.master.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class MasterMenuItem(
    val title: String,
    val icon: ImageVector,
    val color: Color
)
