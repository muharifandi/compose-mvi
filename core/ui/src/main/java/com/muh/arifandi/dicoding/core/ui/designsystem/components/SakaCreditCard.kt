package com.muh.arifandi.dicoding.core.ui.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme

/**
 * Komponen Kartu Kredit standar untuk Saka Design System.
 * Mendukung tampilan data ter-masking (sensor) dan latar belakang kustom (URL, Drawable, atau Gradient).
 *
 * Komponen ini secara otomatis menangani urutan penumpukan elemen dan tata letak informasi
 * sensitif agar tetap terbaca dengan jelas pada berbagai ukuran kontainer.
 *
 * Contoh penggunaan:
 * ```
 * SakaCreditCard(
 *     holderName = "John Smith",
 *     cardType = "Visa Gold",
 *     cardNumber = "1234 5678 9012 3456",
 *     balance = "$12,850.00",
 *     gradientColors = listOf(Color.Blue, Color.Cyan),
 *     isVisible = isCardVisible,
 *     onToggleVisibility = { isCardVisible = !isCardVisible },
 *     backgroundModel = "https://example.com/card_bg.svg" // Bisa URL atau R.drawable
 * )
 * ```
 *
 * @param holderName Nama lengkap pemilik kartu. Akan di-mask (•••• •••••) jika [isVisible] false.
 * @param cardType Jenis atau kategori kartu (contoh: "Visa Gold", "Platinum").
 * @param cardNumber Nomor kartu kredit (16 digit). Digit angka akan di-mask jika [isVisible] false.
 * @param balance Saldo atau limit kartu yang tersedia. Akan di-mask ($••••) jika [isVisible] false.
 * @param gradientColors Daftar warna untuk latar belakang gradient (digunakan sebagai fallback jika [backgroundModel] null).
 * @param isVisible Status visibilitas data sensitif (Nama, Nomor, Saldo).
 * @param onToggleVisibility Callback saat ikon mata diklik untuk mengubah status [isVisible].
 * @param modifier Modifier untuk pengaturan layout (size, padding, dll).
 * @param backgroundModel Model data gambar (URL String, ID Drawable, File, dll) melalui integrasi Coil.
 */
/**
 * Created by Muh. Arifandi on 25/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : core:ui:components
 * File : SakaCreditCard.kt
 */
@Composable
fun SakaCreditCard(
    holderName: String,
    cardType: String,
    cardNumber: String,
    balance: String,
    gradientColors: List<Color>,
    isVisible: Boolean,
    onToggleVisibility: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundModel: Any? = null
) {
    SakaCard(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        isSmallShadow = false
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Background Layer (Support URL & Local Drawable)
            if (backgroundModel != null) {
                SakaAsyncImage(
                    model = backgroundModel,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds,
                    crossfade = true,
                    showPlaceholder = false
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(gradientColors.first())
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(0.55f)
                            .align(Alignment.CenterEnd)
                            .clip(RoundedCornerShape(topStart = 150.dp, bottomStart = 150.dp))
                            .background(gradientColors.last())
                    )
                }
            }

            // Information Layer
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(28.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = if (isVisible) holderName else "•••• •••••",
                        style = SakaTheme.typography.title2,
                        color = Color.White,
                        fontWeight = FontWeight.Normal,
                        fontSize = 26.sp
                    )
                    
                    Icon(
                        imageVector = if (isVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Toggle Visibility",
                        tint = Color.White.copy(alpha = 0.8f),
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { onToggleVisibility() }
                    )
                }
                
                Spacer(modifier = Modifier.height(28.dp))

                Text(
                    text = cardType,
                    style = SakaTheme.typography.body2,
                    color = Color.White.copy(alpha = 0.9f),
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
                
                Spacer(modifier = Modifier.height(14.dp))

                val maskedCardNumber = cardNumber.replace(Regex("\\d"), "•")
                Text(
                    text = if (isVisible) cardNumber else maskedCardNumber,
                    style = SakaTheme.typography.body1,
                    color = Color.White,
                    letterSpacing = 2.sp,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = if (isVisible) balance else "$••••",
                        style = SakaTheme.typography.title2,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "VISA",
                        style = SakaTheme.typography.title3,
                        color = Color.White,
                        fontWeight = FontWeight.Black,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                        fontSize = 22.sp
                    )
                }
            }
        }
    }
}
