package com.muh.arifandi.dicoding.core.ui.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme

import androidx.compose.ui.tooling.preview.Preview
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme

/**
 * Tampilan Loading standar untuk Saka Design System.
 * Menampilkan indikator progres melingkar dengan pesan teks di bawahnya.
 * Mengambil seluruh ruang layar (fillMaxSize) secara default.
 *
 * @param modifier Modifier untuk kustomisasi layout.
 * @param message Pesan teks yang ditampilkan saat loading.
 */
@Composable
fun SakaLoadingView(
    modifier: Modifier = Modifier,
    message: String = "Mohon tunggu..."
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(color = SakaTheme.colors.primary)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = message,
                style = SakaTheme.typography.body3,
                color = SakaTheme.colors.neutralGrey
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SakaLoadingViewPreview() {
    MyApplicationTheme {
        SakaLoadingView()
    }
}
