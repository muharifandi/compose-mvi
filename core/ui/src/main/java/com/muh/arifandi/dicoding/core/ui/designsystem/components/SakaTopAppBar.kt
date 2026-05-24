/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : core:ui
 * File : SakaTopAppBar.kt
 *
 * Description:
 * Top App Bar standar untuk Saka Design System dengan gaya CenterAligned.
 */

package com.muh.arifandi.dicoding.core.ui.designsystem.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme

import androidx.compose.ui.tooling.preview.Preview
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme

/**
 * Top App Bar standar untuk Saka Design System.
 * Menggunakan gaya CenterAligned dengan tipografi Poppins.
 *
 * @param title Judul yang ditampilkan di tengah bar.
 * @param modifier Modifier untuk kustomisasi bar.
 * @param onBackClick Callback untuk tombol kembali. Jika null, tombol kembali disembunyikan.
 * @param actions Kumpulan aksi (ikon/tombol) di sisi kanan bar.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SakaTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = SakaTheme.typography.title3,
                color = SakaTheme.colors.neutralDark
            )
        },
        modifier = modifier,
        navigationIcon = {
            if (onBackClick != null) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = SakaTheme.colors.neutralDark
                    )
                }
            }
        },
        actions = actions,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = SakaTheme.colors.neutralWhite
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun SakaTopAppBarPreview() {
    MyApplicationTheme {
        SakaTopAppBar(
            title = "Detail News",
            onBackClick = {}
        )
    }
}
