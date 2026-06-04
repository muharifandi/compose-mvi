package com.muh.arifandi.dicoding.features.master.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaAsyncImage
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme
import com.muh.arifandi.dicoding.features.master.domain.model.ExchangeRateItem

/**
 * Komponen baris untuk daftar Nilai Tukar (Exchange Rate).
 */
@Composable
fun ExchangeRateRow(
    item: ExchangeRateItem,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1.5f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SakaAsyncImage(
                model = item.flagUrl,
                modifier = Modifier
                    .size(width = 32.dp, height = 24.dp)
                    .clip(RoundedCornerShape(2.dp)),
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = item.countryName,
                style = SakaTheme.typography.body1.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp
                ),
                color = Color(0xFF333333)
            )
        }
        Text(
            text = item.buyRate,
            style = SakaTheme.typography.body1.copy(
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            ),
            color = Color(0xFF333333),
            modifier = Modifier.weight(0.7f),
            textAlign = TextAlign.Center
        )
        Text(
            text = item.sellRate,
            style = SakaTheme.typography.body1.copy(
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            ),
            color = Color(0xFF333333),
            modifier = Modifier.weight(0.7f),
            textAlign = TextAlign.End
        )
    }
}
