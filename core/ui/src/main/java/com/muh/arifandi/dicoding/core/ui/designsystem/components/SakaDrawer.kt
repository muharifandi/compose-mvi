package com.muh.arifandi.dicoding.core.ui.designsystem.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme

/**
 * Arah munculnya Navigation Drawer (Kiri atau Kanan).
 */
enum class SakaDrawerSide {
    LEFT, RIGHT
}

/**
 * Komponen Modal Navigation Drawer kustom yang mendukung muncul dari kiri atau kanan.
 *
 * @param drawerContent Konten yang ditampilkan di dalam drawer.
 * @param modifier Modifier kustom.
 * @param drawerState State untuk mengontrol buka/tutup drawer.
 * @param side Sisi munculnya drawer (LEFT atau RIGHT).
 * @param content Konten utama layar yang akan dibungkus oleh drawer.
 */
@Composable
fun SakaModalDrawer(
    drawerContent: @Composable ColumnScope.() -> Unit,
    modifier: Modifier = Modifier,
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    side: SakaDrawerSide = SakaDrawerSide.LEFT,
    content: @Composable () -> Unit
) {
    // Trik: Mengubah LayoutDirection secara lokal untuk memutar arah slide
    val layoutDirection = if (side == SakaDrawerSide.RIGHT) LayoutDirection.Rtl else LayoutDirection.Ltr

    CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                // Kembalikan ke Ltr untuk konten di dalam drawer agar teks tidak terbalik
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                    ModalDrawerSheet(
                        drawerContainerColor = SakaTheme.colors.neutralWhite,
                        drawerShape = if (side == SakaDrawerSide.LEFT) 
                            RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp) 
                        else 
                            RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp),
                        modifier = Modifier.width(300.dp).fillMaxHeight()
                    ) {
                        drawerContent()
                    }
                }
            },
            modifier = modifier
        ) {
            // Kembalikan ke Ltr untuk konten utama agar tetap normal
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                content()
            }
        }
    }
}

/**
 * Item menu standar untuk digunakan di dalam [SakaModalDrawer].
 *
 * @param label Teks label yang ditampilkan di samping ikon.
 * @param icon Ikon yang mewakili menu.
 * @param isSelected Status apakah menu ini sedang aktif/terpilih.
 * @param onClick Callback saat menu diklik.
 * @param modifier Modifier kustom untuk item drawer.
 */
@Composable
fun SakaDrawerItem(
    label: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationDrawerItem(
        label = { Text(text = label, style = SakaTheme.typography.body2) },
        selected = isSelected,
        onClick = onClick,
        icon = { Icon(imageVector = icon, contentDescription = null) },
        shape = RoundedCornerShape(12.dp),
        colors = NavigationDrawerItemDefaults.colors(
            selectedContainerColor = SakaTheme.colors.primarySubtle,
            selectedIconColor = SakaTheme.colors.primary,
            selectedTextColor = SakaTheme.colors.primary,
            unselectedIconColor = SakaTheme.colors.neutralGrey,
            unselectedTextColor = SakaTheme.colors.neutralDark
        ),
        modifier = modifier.padding(horizontal = 12.dp, vertical = 4.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun SakaDrawerLeftPreview() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)
    MyApplicationTheme {
        SakaModalDrawer(
            side = SakaDrawerSide.LEFT,
            drawerState = drawerState,
            drawerContent = {
                SakaDrawerItem(label = "Home", icon = Icons.Default.Home, isSelected = true, onClick = {})
                SakaDrawerItem(label = "Settings", icon = Icons.Default.Settings, isSelected = false, onClick = {})
            }
        ) {
            Scaffold { padding ->
                androidx.compose.foundation.layout.Box(
                    modifier = Modifier.padding(padding).fillMaxHeight(),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    Text("Main Content (Left Drawer Open)")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SakaDrawerRightPreview() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)
    MyApplicationTheme {
        SakaModalDrawer(
            side = SakaDrawerSide.RIGHT,
            drawerState = drawerState,
            drawerContent = {
                SakaDrawerItem(label = "Profile", icon = Icons.Default.Home, isSelected = true, onClick = {})
                SakaDrawerItem(label = "Logout", icon = Icons.Default.Settings, isSelected = false, onClick = {})
            }
        ) {
            Scaffold { padding ->
                androidx.compose.foundation.layout.Box(
                    modifier = Modifier.padding(padding).fillMaxHeight(),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    Text("Main Content (Right Drawer Open)")
                }
            }
        }
    }
}
