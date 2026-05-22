package com.muh.arifandi.dicoding.core.ui.designsystem.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme
import com.muh.arifandi.dicoding.core.ui.util.sakaShadow

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme

/**
 * Komponen Card kustom dengan dukungan bayangan (shadow) sesuai parameter Figma.
 * Menggunakan kustomisasi Blur, X, dan Y offset untuk tampilan elevasi yang halus.
 *
 * @param modifier Modifier untuk layout.
 * @param isSmallShadow Jika true, menggunakan offset Y negatif (-5dp) untuk bayangan kecil.
 * @param shape Bentuk sudut kartu (default 12dp).
 * @param backgroundColor Warna latar belakang kartu.
 * @param content Konten yang akan diletakkan di dalam kartu.
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
