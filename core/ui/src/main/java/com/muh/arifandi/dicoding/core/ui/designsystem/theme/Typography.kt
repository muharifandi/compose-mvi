/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : core:ui
 * File : Typography.kt
 *
 * Description:
 * Data class untuk mendefinisikan skala tipografi dalam Saka Design System.
 */

package com.muh.arifandi.dicoding.core.ui.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
@Immutable
data class SakaTypography(
    val title1: TextStyle,
    val title2: TextStyle,
    val title3: TextStyle,
    val body1: TextStyle,
    val body2: TextStyle,
    val body3: TextStyle,
    val caption1: TextStyle,
    val caption2: TextStyle
)

val LocalSakaTypography = staticCompositionLocalOf {
    SakaTypography(
        title1 = TextStyle.Default,
        title2 = TextStyle.Default,
        title3 = TextStyle.Default,
        body1 = TextStyle.Default,
        body2 = TextStyle.Default,
        body3 = TextStyle.Default,
        caption1 = TextStyle.Default,
        caption2 = TextStyle.Default
    )
}
