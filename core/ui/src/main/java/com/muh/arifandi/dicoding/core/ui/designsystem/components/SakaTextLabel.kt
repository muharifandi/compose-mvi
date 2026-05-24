/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : core:ui
 * File : SakaTextLabel.kt
 *
 * Description:
 * Komponen Text Label / Badge kustom untuk Saka design system, berguna untuk tag atau indikator status.
 */

package com.muh.arifandi.dicoding.core.ui.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme

/**
 * Variants for SakaTextLabel to define its color scheme.
 */
enum class SakaTextLabelVariant {
    PRIMARY,
    SUCCESS,
    WARNING,
    ERROR,
    INFO,
    NEUTRAL
}

/**
 * Custom Text Label / Badge component for Saka design system.
 * Useful for tags, status indicators, or category labels.
 *
 * @param text The text to be displayed.
 * @param variant The color variant of the label.
 * @param modifier Custom modifier for the component.
 * @param textStyle Custom text style (defaults to caption1).
 */
@Composable
fun SakaTextLabel(
    text: String,
    modifier: Modifier = Modifier,
    variant: SakaTextLabelVariant = SakaTextLabelVariant.NEUTRAL,
    textStyle: TextStyle = SakaTheme.typography.caption1
) {
    val backgroundColor = when (variant) {
        SakaTextLabelVariant.PRIMARY -> SakaTheme.colors.primarySubtle
        SakaTextLabelVariant.SUCCESS -> SakaTheme.colors.semanticSuccess.copy(alpha = 0.12f)
        SakaTextLabelVariant.WARNING -> SakaTheme.colors.semanticWarning.copy(alpha = 0.12f)
        SakaTextLabelVariant.ERROR -> SakaTheme.colors.semanticError.copy(alpha = 0.12f)
        SakaTextLabelVariant.INFO -> SakaTheme.colors.semanticInfo.copy(alpha = 0.12f)
        SakaTextLabelVariant.NEUTRAL -> SakaTheme.colors.neutralPlatinum
    }

    val contentColor = when (variant) {
        SakaTextLabelVariant.PRIMARY -> SakaTheme.colors.primary
        SakaTextLabelVariant.SUCCESS -> SakaTheme.colors.semanticSuccess
        SakaTextLabelVariant.WARNING -> SakaTheme.colors.semanticWarning
        SakaTextLabelVariant.ERROR -> SakaTheme.colors.semanticError
        SakaTextLabelVariant.INFO -> SakaTheme.colors.semanticInfo
        SakaTextLabelVariant.NEUTRAL -> SakaTheme.colors.neutralDark
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(backgroundColor)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            style = textStyle,
            color = contentColor
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SakaTextLabelPreview() {
    MyApplicationTheme {
        androidx.compose.foundation.layout.Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)
        ) {
            SakaTextLabel(text = "Primary", variant = SakaTextLabelVariant.PRIMARY)
            SakaTextLabel(text = "Success", variant = SakaTextLabelVariant.SUCCESS)
            SakaTextLabel(text = "Warning", variant = SakaTextLabelVariant.WARNING)
            SakaTextLabel(text = "Error", variant = SakaTextLabelVariant.ERROR)
            SakaTextLabel(text = "Info", variant = SakaTextLabelVariant.INFO)
            SakaTextLabel(text = "Neutral", variant = SakaTextLabelVariant.NEUTRAL)
        }
    }
}
