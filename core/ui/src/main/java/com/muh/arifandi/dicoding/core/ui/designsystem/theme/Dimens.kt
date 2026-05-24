/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : core:ui
 * File : Dimens.kt
 *
 * Description:
 * Definisi dimensi responsif untuk Saka Design System.
 */

package com.muh.arifandi.dicoding.core.ui.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Dimens standar untuk Saka Design System.
 * Nilai akan menyesuaikan secara otomatis berdasarkan ukuran layar.
 */
@Immutable
data class SakaDimens(
    val spaceNone: Dp = 0.dp,
    val spaceXXS: Dp = 2.dp,
    val spaceXS: Dp = 4.dp,
    val spaceS: Dp = 8.dp,
    val spaceM: Dp = 12.dp,
    val spaceL: Dp = 16.dp,
    val spaceXL: Dp = 24.dp,
    val spaceXXL: Dp = 32.dp,
    val spaceHuge: Dp = 48.dp,
    
    // Dimensi Responsif (Persentase)
    val screenHeight: Dp = 0.dp,
    val screenWidth: Dp = 0.dp,
    val isTablet: Boolean = false
)

val LocalSakaDimens = staticCompositionLocalOf { SakaDimens() }

@Composable
fun rememberSakaDimens(): SakaDimens {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val isTablet = configuration.screenWidthDp >= 600

    return SakaDimens(
        screenHeight = screenHeight,
        screenWidth = screenWidth,
        isTablet = isTablet,
        // Anda bisa menambahkan logika kustom di sini jika ingin dimens berbeda untuk tablet
        spaceXL = if (isTablet) 32.dp else 24.dp,
        spaceXXL = if (isTablet) 48.dp else 32.dp
    )
}
