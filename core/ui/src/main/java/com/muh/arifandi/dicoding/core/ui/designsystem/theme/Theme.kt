package com.muh.arifandi.dicoding.core.ui.designsystem.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)


@Immutable
data class SakaColors(
    val primary: Color,
    val primaryLight: Color,
    val primaryLighter: Color,
    val primarySubtle: Color,
    val neutralDark: Color,
    val neutralGrey: Color,
    val neutralLightGrey: Color,
    val neutralSilver: Color,
    val neutralPlatinum: Color,
    val neutralWhite: Color,
    val semanticError: Color,
    val semanticInfo: Color,
    val semanticWarning: Color,
    val semanticSuccess: Color,
    val semanticAttention: Color
)

val LocalSakaColors = staticCompositionLocalOf {
    SakaColors(
        primary = Color.Unspecified,
        primaryLight = Color.Unspecified,
        primaryLighter = Color.Unspecified,
        primarySubtle = Color.Unspecified,
        neutralDark = Color.Unspecified,
        neutralGrey = Color.Unspecified,
        neutralLightGrey = Color.Unspecified,
        neutralSilver = Color.Unspecified,
        neutralPlatinum = Color.Unspecified,
        neutralWhite = Color.Unspecified,
        semanticError = Color.Unspecified,
        semanticInfo = Color.Unspecified,
        semanticWarning = Color.Unspecified,
        semanticSuccess = Color.Unspecified,
        semanticAttention = Color.Unspecified
    )
}

private val LightColorScheme = lightColorScheme(
    primary = PrimaryBase,
    onPrimary = Color.White,
    primaryContainer = PrimarySubtle,
    onPrimaryContainer = PrimaryBase,
    secondary = SemanticInfo,
    error = SemanticError,
    background = NeutralWhite,
    surface = NeutralWhite,
    onSurface = NeutralDark
)

@Composable
fun MyApplicationTheme(
    content: @Composable () -> Unit
) {
    val customColors = SakaColors(
        primary = PrimaryBase,
        primaryLight = PrimaryLight,
        primaryLighter = PrimaryLighter,
        primarySubtle = PrimarySubtle,
        neutralDark = NeutralDark,
        neutralGrey = NeutralGrey,
        neutralLightGrey = NeutralLightGrey,
        neutralSilver = NeutralSilver,
        neutralPlatinum = NeutralPlatinum,
        neutralWhite = NeutralWhite,
        semanticError = SemanticError,
        semanticInfo = SemanticInfo,
        semanticWarning = SemanticWarning,
        semanticSuccess = SemanticSuccess,
        semanticAttention = SemanticAttention
    )

    CompositionLocalProvider(
        LocalSakaColors provides customColors,
        LocalSakaTypography provides SakaTypographyStyles
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}

object SakaTheme {
    val colors: SakaColors @Composable get() = LocalSakaColors.current
    val typography: SakaTypography @Composable get() = LocalSakaTypography.current
}
