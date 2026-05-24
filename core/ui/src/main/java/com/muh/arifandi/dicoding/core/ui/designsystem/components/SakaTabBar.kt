/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : core:ui
 * File : SakaTabBar.kt
 *
 * Description:
 * Komponen Tab Bar dinamis dengan animasi halus dan dukungan scroll otomatis.
 */

package com.muh.arifandi.dicoding.core.ui.designsystem.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme

/**
 * Data class untuk merepresentasikan item pada Tab Bar.
 */
data class SakaTabItem(
    val label: String,
    val icon: ImageVector
)

/**
 * Komponen Tab Bar dinamis dengan animasi halus (smooth).
 * Mendukung scroll otomatis jika jumlah item melebihi kapasitas layar.
 *
 * @param items Daftar item tab menggunakan model [SakaTabItem].
 * @param selectedIndex Indeks tab yang sedang terpilih saat ini.
 * @param onItemSelected Callback yang dipanggil saat sebuah tab dipilih oleh pengguna.
 * @param modifier Modifier untuk kustomisasi tata letak eksternal.
 */
@Composable
fun SakaTabBar(
    items: List<SakaTabItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Surface(
        modifier = modifier.fillMaxWidth(),
        color = SakaTheme.colors.neutralWhite,
        shadowElevation = 4.dp // Sedikit dikurangi agar lebih elegan
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState)
                .padding(vertical = 12.dp, horizontal = 8.dp),
            horizontalArrangement = if (items.size <= 4) Arrangement.SpaceAround else Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, item ->
                SakaTabEntry(
                    item = item,
                    isSelected = index == selectedIndex,
                    onClick = { onItemSelected(index) }
                )
                if (items.size > 4 && index < items.size - 1) {
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    }
}

/**
 * Item individual dengan pembersihan ripple (agar tidak ada abu-abu tertinggal)
 * dan animasi transisi warna + ukuran yang smooth.
 */
@Composable
private fun SakaTabEntry(
    item: SakaTabItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    // 1. Animasi warna latar belakang yang lebih halus
    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) SakaTheme.colors.primary else Color.Transparent,
        animationSpec = tween(durationMillis = 400),
        label = "bgColor"
    )

    // 2. Animasi warna konten (ikon & teks)
    val contentColor by animateColorAsState(
        targetValue = if (isSelected) SakaTheme.colors.neutralWhite else SakaTheme.colors.neutralGrey,
        animationSpec = tween(durationMillis = 400),
        label = "contentColor"
    )

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(100.dp))
            .background(backgroundColor)
            // 3. MENGHILANGKAN RIPPLE ABU-ABU: Menggunakan interactionSource tanpa indication
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null 
            ) { onClick() }
            // 4. ANIMASI UKURAN: Menggunakan spring agar tidak kaku saat melebar
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = 0.8f,
                    stiffness = 300f
                )
            )
            .padding(horizontal = 16.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = item.label,
                tint = contentColor,
                modifier = Modifier.size(24.dp)
            )
            
            if (isSelected) {
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = item.label,
                    style = SakaTheme.typography.caption1,
                    color = contentColor,
                    maxLines = 1
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SakaTabBarInteractivePreview() {
    val items = listOf(
        SakaTabItem("Home", Icons.Outlined.Home),
        SakaTabItem("Search", Icons.Outlined.Search),
        SakaTabItem("Message", Icons.Outlined.Mail),
        SakaTabItem("Setting", Icons.Outlined.Settings)
    )

    var selectedIndex by remember { mutableIntStateOf(0) }

    MyApplicationTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            SakaTabBar(
                items = items,
                selectedIndex = selectedIndex,
                onItemSelected = { selectedIndex = it }
            )
        }
    }
}
