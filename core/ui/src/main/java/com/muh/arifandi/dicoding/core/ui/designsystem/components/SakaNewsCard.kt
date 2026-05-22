package com.muh.arifandi.dicoding.core.ui.designsystem.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme
import com.muh.arifandi.dicoding.core.ui.util.clickableDebounced

import androidx.compose.ui.tooling.preview.Preview
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme

/**
 * Kartu Item Berita standar untuk Saka Design System.
 * Menampilkan gambar asinkron, judul tebal, dan deskripsi singkat.
 * Sudah mendukung efek bayangan (shadow) kustom dan debounced click.
 *
 * @param title Judul berita.
 * @param imageUrl URL gambar berita.
 * @param description Ringkasan atau deskripsi berita.
 * @param onClick Callback saat kartu diklik.
 * @param modifier Modifier untuk pengaturan layout tambahan.
 */
@Composable
fun SakaNewsCard(
    title: String,
    imageUrl: String,
    description: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    SakaCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickableDebounced { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(12.dp)
        ) {
            SakaAsyncImage(
                model = imageUrl,
                contentDescription = title,
                modifier = Modifier
                    .size(100.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = title,
                    style = SakaTheme.typography.title3,
                    color = SakaTheme.colors.neutralDark,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = description,
                    style = SakaTheme.typography.body3,
                    color = SakaTheme.colors.neutralGrey,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SakaNewsCardPreview() {
    MyApplicationTheme {
        SakaNewsCard(
            title = "Saka Design System is Live!",
            imageUrl = "",
            description = "Saka Design System provides a robust foundation for building high-quality Android applications with Jetpack Compose.",
            onClick = {}
        )
    }
}
