package com.muh.arifandi.dicoding.core.ui.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
 * Tampilan State Kosong (Empty State) untuk Saka Design System.
 * Digunakan saat data tidak ditemukan atau list dalam keadaan kosong.
 *
 * @param message Pesan yang menjelaskan kondisi data kosong.
 * @param modifier Modifier kustom.
 */
@Composable
fun SakaEmptyView(
    message: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize().padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = SakaTheme.typography.body2,
            color = SakaTheme.colors.neutralGrey,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SakaEmptyViewPreview() {
    MyApplicationTheme {
        SakaEmptyView(message = "Tidak ada data yang tersedia saat ini.")
    }
}
