package com.muh.arifandi.dicoding.core.ui.designsystem.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme
import com.muh.arifandi.dicoding.core.ui.util.sakaShadow

/**
 * Komponen Card dasar untuk Saka Design System.
 * Memberikan efek elevasi bayangan (shadow) yang halus dan latar belakang warna yang konsisten.
 *
 * Komponen ini membungkus kontainer [Surface] dengan modifikasi bayangan kustom untuk memberikan
 * kedalaman visual pada elemen UI.
 *
 * Contoh penggunaan:
 * ```
 * SakaCard(
 *     modifier = Modifier.padding(16.dp),
 *     isSmallShadow = true
 * ) {
 *     Text("Konten Kartu")
 * }
 * ```
 *
 * @param modifier Modifier untuk pengaturan layout (misal: padding, fillMaxWidth).
 * @param isSmallShadow Jika true, memberikan offset bayangan yang lebih tipis/kecil.
 * @param shape Bentuk sudut kartu (default: RoundedCornerShape 12.dp).
 * @param backgroundColor Warna latar belakang kartu (default: NeutralWhite).
 * @param content Konten Composable yang akan ditampilkan di dalam kartu.
 */
/**
 * Created by Muh. Arifandi on 25/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : core:ui:components
 * File : SakaCard.kt
 */
@Composable
fun SakaCard(
    modifier: Modifier = Modifier,
    isSmallShadow: Boolean = false,
    shape: Shape = RoundedCornerShape(12.dp),
    backgroundColor: Color = SakaTheme.colors.neutralWhite,
    content: @Composable () -> Unit
) {
    val shadowOffsetY = if (isSmallShadow) (-5).dp else 4.dp

    Surface(
        modifier = modifier
            .sakaShadow(
                offsetY = shadowOffsetY,
                borderRadius = 12.dp,
                blurRadius = 30.dp
            ),
        shape = shape,
        color = backgroundColor,
        content = content
    )
}

@Preview(showBackground = true)
@Composable
private fun SakaCardPreview() {
    MyApplicationTheme {
        Column(modifier = Modifier.padding(32.dp)) {
            SakaCard(
                modifier = Modifier.fillMaxWidth().height(100.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text("Large Shadow Card (Y: 4dp)", style = SakaTheme.typography.body1)
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            SakaCard(
                modifier = Modifier.fillMaxWidth().height(100.dp),
                isSmallShadow = true
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text("Small Shadow Card (Y: -5dp)", style = SakaTheme.typography.body1)
                }
            }
        }
    }
}
