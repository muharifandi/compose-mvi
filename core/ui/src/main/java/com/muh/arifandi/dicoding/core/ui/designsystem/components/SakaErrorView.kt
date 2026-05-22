package com.muh.arifandi.dicoding.core.ui.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme

import androidx.compose.ui.tooling.preview.Preview
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme

/**
 * Tampilan Error standar untuk Saka Design System.
 * Digunakan untuk menampilkan pesan kegagalan dan menyediakan tombol untuk mencoba kembali.
 *
 * @param message Pesan kesalahan yang ingin disampaikan ke pengguna.
 * @param onRetry Callback saat tombol "Coba Lagi" diklik.
 * @param modifier Modifier kustom.
 */
@Composable
fun SakaErrorView(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            style = SakaTheme.typography.body2,
            color = SakaTheme.colors.semanticError,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))
        SakaButton(
            text = "Coba Lagi",
            onClick = onRetry
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SakaErrorViewPreview() {
    MyApplicationTheme {
        SakaErrorView(
            message = "Koneksi internet terputus. Silakan coba beberapa saat lagi.",
            onRetry = {}
        )
    }
}
