package com.muh.arifandi.dicoding.core.ui.designsystem.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme

/**
 * Komponen Gambar Asinkron yang dioptimalkan untuk Saka Design System.
 * Menggunakan library Coil untuk manajemen caching dan pemuatan gambar yang efisien dari berbagai sumber.
 *
 * Komponen ini mendukung pemuatan gambar dari URL internet, Resource Drawable lokal, file, dan lainnya.
 * Dilengkapi dengan transisi crossfade dan placeholder otomatis untuk meningkatkan pengalaman pengguna.
 *
 * Contoh penggunaan:
 * ```
 * SakaAsyncImage(
 *     model = "https://example.com/image.jpg",
 *     modifier = Modifier.size(100.dp),
 *     contentScale = ContentScale.Crop
 * )
 * ```
 *
 * @param model Sumber data gambar (URL String, [android.graphics.drawable.Drawable], resource ID, dll).
 * @param modifier Modifier kustom untuk pengaturan layout (misal: size, clip).
 * @param contentDescription Deskripsi aksesibilitas untuk gambar (penting untuk screen reader).
 * @param contentScale Cara gambar menyesuaikan ukuran kontainer (default: ContentScale.Crop).
 * @param crossfade Jika true, memberikan efek transisi halus saat gambar muncul (default: true).
 * @param showPlaceholder Jika true, menampilkan ikon placeholder saat loading atau error (default: true).
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
            .allowHardware(false) // Fix: Software rendering doesn't support hardware bitmaps
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
