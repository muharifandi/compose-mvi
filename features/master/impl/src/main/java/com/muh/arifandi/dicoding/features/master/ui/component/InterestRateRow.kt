package com.muh.arifandi.dicoding.features.master.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme
import com.muh.arifandi.dicoding.features.master.domain.model.InterestRateItem

/**
 * Komponen baris untuk daftar Suku Bunga.
 */
@Composable
fun InterestRateRow(
    item: InterestRateItem,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item.kind,
            style = SakaTheme.typography.body1.copy(
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            ),
            color = Color(0xFF333333),
            modifier = Modifier.weight(1.5f)
        )
        Text(
            text = item.deposit,
            style = SakaTheme.typography.body1.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp
            ),
            color = Color(0xFF333333),
            modifier = Modifier.weight(0.7f),
            textAlign = TextAlign.Center
        )
        Text(
            text = item.rate,
            style = SakaTheme.typography.body1.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            ),
            color = if (item.isPrimary) Color(0xFF3629B7) else Color(0xFF3629B7).copy(alpha = 0.8f),
            modifier = Modifier.weight(0.5f),
            textAlign = TextAlign.End
        )
    }
}
