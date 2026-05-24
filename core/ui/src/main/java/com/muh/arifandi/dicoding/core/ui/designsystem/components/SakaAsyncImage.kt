/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : core:ui
 * File : SakaAsyncImage.kt
 *
 * Description:
 * Komponen Gambar Asinkron yang dioptimalkan untuk Saka Design System menggunakan Coil.
 */

package com.muh.arifandi.dicoding.core.ui.designsystem.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

import androidx.compose.foundation.layout.size
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme

/**
 * Komponen Gambar Asinkron yang dioptimalkan untuk Saka Design System.
 * Menggunakan library Coil untuk manajemen caching dan pemuatan gambar yang efisien.
 *
 * @param model URL gambar atau resource data lainnya.
 * @param modifier Modifier kustom (misal: size, clip).
 * @param contentDescription Deskripsi aksesibilitas untuk gambar.
 * @param contentScale Cara gambar menyesuaikan ukuran kontainer (default: Crop).
 * @param crossfade Jika true, memberikan efek transisi halus saat gambar muncul.
 */
@Composable
fun SakaAsyncImage(
    model: Any?,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
    crossfade: Boolean = true,
    showPlaceholder: Boolean = true
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(model)
            .crossfade(crossfade)
            .build(),
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier,
        placeholder = if (showPlaceholder) rememberVectorPainter(Icons.Default.Image) else null,
        error = if (showPlaceholder) rememberVectorPainter(Icons.Default.Warning) else null
    )
}

@Preview(showBackground = true)
@Composable
private fun SakaAsyncImagePreview() {
    MyApplicationTheme {
        SakaAsyncImage(
            model = null, // Will show placeholder in preview
            modifier = Modifier.size(100.dp),
            contentDescription = "Preview Image"
        )
    }
}
