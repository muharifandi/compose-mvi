package com.muh.arifandi.dicoding.core.ui.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme
import com.muh.arifandi.dicoding.core.ui.util.clickableDebounced

import androidx.compose.material3.IconButton

/**
 * Komponen Navigation Bar kustom yang digunakan untuk layar Onboarding atau Form.
 * Memiliki ikon kembali yang dapat diklik dan judul yang rata kiri sesuai desain Saka.
 *
 * @param title Judul layar.
 * @param onBackClick Callback saat tombol kembali diklik.
 * @param modifier Modifier kustom.
 * @param backgroundColor Warna latar belakang (default: Transparan/White).
 * @param contentColor Warna teks dan ikon (default: NeutralDark).
 */
@Composable
fun SakaNavigationBar(
    title: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Transparent,
    contentColor: Color = SakaTheme.colors.neutralDark
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(backgroundColor),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.padding(start = 4.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = "Back",
                tint = contentColor
            )
        }
        Text(
            text = title,
            style = SakaTheme.typography.title3,
            color = contentColor,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SakaNavigationBarPreview() {
    MyApplicationTheme {
        androidx.compose.foundation.layout.Column {
            SakaNavigationBar(
                title = "Forgot password",
                onBackClick = {},
                backgroundColor = Color.LightGray,
                contentColor = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            SakaNavigationBar(
                title = "Sign in",
                onBackClick = {}
            )
        }
    }
}
