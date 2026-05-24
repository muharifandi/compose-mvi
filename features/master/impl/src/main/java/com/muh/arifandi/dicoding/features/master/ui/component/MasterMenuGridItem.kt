package com.muh.arifandi.dicoding.features.master.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaCard
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme
import com.muh.arifandi.dicoding.features.master.domain.model.MasterMenuItem

/**
 * Komponen item grid menu untuk layar utama Fitur Master.
 * Menampilkan ikon di dalam kartu putih statis dengan teks label di bawahnya.
 *
 * Komponen ini dirancang untuk bekerja di dalam grid (misal: 3 kolom) dan secara otomatis
 * menyesuaikan orientasi kontennya agar tetap terpusat.
 *
 * @param item Data model [MasterMenuItem] yang berisi judul, ikon, dan warna tema menu.
 * @param modifier Modifier untuk pengaturan layout tambahan dari luar grid.
 */
@Composable
fun MasterMenuGridItem(
    item: MasterMenuItem,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        SakaCard(
            modifier = Modifier
                .size(80.dp), // Ukuran standar untuk grid 3-kolom agar muat tanpa scroll
            shape = RoundedCornerShape(16.dp),
            backgroundColor = Color.White,
            isSmallShadow = false
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.title,
                    tint = item.color,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
        
        Spacer(modifier = Modifier.height(6.dp))
        
        Text(
            text = item.title,
            style = SakaTheme.typography.caption1,
            color = Color(0xFF8E8E93),
            textAlign = TextAlign.Center,
            lineHeight = 12.sp,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            maxLines = 2,
            modifier = Modifier.padding(horizontal = 2.dp)
        )
    }
}
