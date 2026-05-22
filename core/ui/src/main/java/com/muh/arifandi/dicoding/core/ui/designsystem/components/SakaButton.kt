package com.muh.arifandi.dicoding.core.ui.designsystem.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme

/**
 * Kategori Button yang tersedia di Saka Design System.
 */
enum class SakaButtonType {
    PRIMARY,    // Pill shape, background biru
    ERROR,      // Text button, warna merah
    NEUTRAL,    // Text button, warna abu-abu
    ICON,       // Circular button dengan icon
    LINK        // Text button, warna biru brand
}

/**
 * Komponen Button Tunggal untuk Saka Design System.
 * Cukup tentukan [type] untuk mendapatkan gaya yang sesuai dengan desain.
 *
 * @param onClick Aksi saat tombol diklik.
 * @param modifier Modifier untuk layouting.
 * @param type Jenis tombol (Primary, Error, Neutral, Icon, Link).
 * @param text Teks label (diperlukan untuk tipe selain ICON).
 * @param icon Ikon (diperlukan untuk tipe ICON).
 * @param enabled Status aktif tombol.
 * @param isLoading Menampilkan indikator loading (hanya untuk tipe PRIMARY).
 */
@Composable
fun SakaButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    type: SakaButtonType = SakaButtonType.PRIMARY,
    text: String? = null,
    icon: ImageVector? = null,
    enabled: Boolean = true,
    isLoading: Boolean = false
) {
    when (type) {
        SakaButtonType.PRIMARY -> {
            Button(
                onClick = onClick,
                modifier = modifier.fillMaxWidth().height(56.dp),
                enabled = enabled && !isLoading,
                shape = RoundedCornerShape(100.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = SakaTheme.colors.primary,
                    contentColor = SakaTheme.colors.neutralWhite,
                    disabledContainerColor = SakaTheme.colors.primarySubtle,
                    disabledContentColor = SakaTheme.colors.neutralWhite
                )
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = SakaTheme.colors.neutralWhite,
                        strokeWidth = 2.dp
                    )
                } else {
                    Text(text = text.orEmpty(), style = SakaTheme.typography.title3)
                }
            }
        }

        SakaButtonType.ERROR -> {
            TextButton(
                onClick = onClick,
                modifier = modifier,
                enabled = enabled,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = SakaTheme.colors.semanticError,
                    disabledContentColor = SakaTheme.colors.neutralSilver
                )
            ) {
                Text(text = text.orEmpty(), style = SakaTheme.typography.body1)
            }
        }

        SakaButtonType.NEUTRAL -> {
            TextButton(
                onClick = onClick,
                modifier = modifier,
                enabled = enabled,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = SakaTheme.colors.neutralGrey,
                    disabledContentColor = SakaTheme.colors.neutralSilver
                )
            ) {
                Text(text = text.orEmpty(), style = SakaTheme.typography.body1)
            }
        }

        SakaButtonType.ICON -> {
            Button(
                onClick = onClick,
                modifier = modifier.size(56.dp),
                enabled = enabled,
                shape = CircleShape,
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = SakaTheme.colors.primary,
                    contentColor = SakaTheme.colors.neutralWhite,
                    disabledContainerColor = SakaTheme.colors.primarySubtle,
                    disabledContentColor = SakaTheme.colors.neutralWhite
                )
            ) {
                Icon(
                    imageVector = icon ?: Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        SakaButtonType.LINK -> {
            TextButton(
                onClick = onClick,
                modifier = modifier,
                enabled = enabled,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = SakaTheme.colors.primary
                )
            ) {
                Text(text = text.orEmpty(), style = SakaTheme.typography.caption1)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SakaButtonTypesPreview() {
    MyApplicationTheme {
        androidx.compose.foundation.layout.Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp)
        ) {
            // Primary Disabled (Atas di gambar)
            SakaButton(type = SakaButtonType.PRIMARY, text = "Sign in", enabled = false, onClick = {})
            
            // Primary Active
            SakaButton(type = SakaButtonType.PRIMARY, text = "Sign in", onClick = {})
            
            // Error (Red)
            SakaButton(type = SakaButtonType.ERROR, text = "Button", onClick = {})
            
            // Neutral (Grey)
            SakaButton(type = SakaButtonType.NEUTRAL, text = "Button", onClick = {})
            
            androidx.compose.foundation.layout.Row(
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                // Icon Button Active
                SakaButton(type = SakaButtonType.ICON, icon = Icons.AutoMirrored.Filled.ArrowForward, onClick = {})
                
                // Icon Button Disabled
                SakaButton(type = SakaButtonType.ICON, icon = Icons.AutoMirrored.Filled.ArrowForward, enabled = false, onClick = {})
                
                // Link
                SakaButton(type = SakaButtonType.LINK, text = "Link", onClick = {})
            }
        }
    }
}
