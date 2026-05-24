/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : core:ui
 * File : SakaSwitch.kt
 *
 * Description:
 * Komponen Switch kustom untuk Saka Design System yang mengikuti skema warna brand.
 */

package com.muh.arifandi.dicoding.core.ui.designsystem.components

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme

/**
 * Komponen Switch kustom untuk Saka Design System.
 * Mengikuti skema warna brand dengan transisi yang halus antara keadaan aktif dan tidak aktif.
 *
 * @param checked Status switch saat ini (true jika aktif).
 * @param onCheckedChange Callback saat status switch berubah.
 * @param modifier Modifier untuk kustomisasi layout.
 * @param enabled Menentukan apakah switch dapat berinteraksi.
 */
@Composable
fun SakaSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        enabled = enabled,
        colors = SwitchDefaults.colors(
            checkedThumbColor = SakaTheme.colors.neutralWhite,
            checkedTrackColor = SakaTheme.colors.primary,
            checkedBorderColor = SakaTheme.colors.primary,
            uncheckedThumbColor = SakaTheme.colors.neutralWhite,
            uncheckedTrackColor = SakaTheme.colors.neutralSilver,
            uncheckedBorderColor = SakaTheme.colors.neutralSilver,
            disabledCheckedThumbColor = SakaTheme.colors.neutralWhite.copy(alpha = 0.5f),
            disabledCheckedTrackColor = SakaTheme.colors.primarySubtle,
            disabledUncheckedThumbColor = SakaTheme.colors.neutralWhite.copy(alpha = 0.5f),
            disabledUncheckedTrackColor = SakaTheme.colors.neutralPlatinum
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun SakaSwitchPreview() {
    MyApplicationTheme {
        var checked1 by remember { mutableStateOf(true) }
        var checked2 by remember { mutableStateOf(false) }
        
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = spacedBy(24.dp)
        ) {
            // State Aktif (Biru)
            SakaSwitch(
                checked = checked1,
                onCheckedChange = { checked1 = it }
            )
            
            // State Tidak Aktif (Abu-abu)
            SakaSwitch(
                checked = checked2,
                onCheckedChange = { checked2 = it }
            )
        }
    }
}
