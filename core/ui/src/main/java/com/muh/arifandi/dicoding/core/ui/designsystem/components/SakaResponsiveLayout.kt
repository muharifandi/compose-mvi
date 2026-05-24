/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : core:ui
 * File : SakaResponsiveLayout.kt
 *
 * Description:
 * Komponen Layout Utama yang otomatis menangani responsivitas dan sticky footer.
 */

package com.muh.arifandi.dicoding.core.ui.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme

/**
 * Komponen Layout Utama yang otomatis menangani responsivitas.
 * 1. Otomatis mendukung scroll jika konten melebihi layar.
 * 2. Otomatis membagi ruang antara konten utama dan footer (Sticky Footer).
 * 3. Menggunakan padding standar dari SakaTheme.
 *
 * @param modifier Modifier kustom.
 * @param content Konten utama yang akan berada di bagian atas/tengah.
 * @param footer Konten opsional yang akan selalu menempel di bagian bawah layar.
 */
@Composable
fun SakaResponsiveColumn(
    modifier: Modifier = Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    content: @Composable ColumnScope.() -> Unit,
    footer: @Composable (ColumnScope.() -> Unit)? = null
) {
    val dimens = SakaTheme.dimens

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = dimens.spaceXL)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = horizontalAlignment,
        verticalArrangement = Arrangement.SpaceBetween // Ini yang membuat footer menempel di bawah
    ) {
        // Bagian Konten Utama
        Column(
            horizontalAlignment = horizontalAlignment,
            modifier = Modifier.weight(1f, fill = false)
        ) {
            content()
        }

        // Bagian Footer (Jika ada)
        if (footer != null) {
            Column(
                horizontalAlignment = horizontalAlignment,
                modifier = Modifier.padding(vertical = dimens.spaceL)
            ) {
                footer()
            }
        }
    }
}
