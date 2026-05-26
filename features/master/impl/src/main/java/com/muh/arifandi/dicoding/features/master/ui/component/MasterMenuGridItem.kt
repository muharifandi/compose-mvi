/**
 * Created by Muh. Arifandi on 25/05/2026
 * Email : arif76440@gmail.com
 * Project : SakaAndroid
 * File : MasterMenuGridItem.kt
 */

package com.muh.arifandi.dicoding.features.master.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaCard
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme
import com.muh.arifandi.dicoding.features.master.domain.model.MasterMenuItem
import com.muh.arifandi.dicoding.features.master.ui.mapper.toComposeColor
import com.muh.arifandi.dicoding.features.master.ui.mapper.toIcon

@Composable
fun MasterMenuGridItem(
    item: MasterMenuItem,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() },
    ) {
        SakaCard(
            modifier = Modifier
                .size(80.dp),
            shape = RoundedCornerShape(16.dp),
            backgroundColor = Color.White,
            isSmallShadow = false
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = item.type.toIcon(),
                    contentDescription = item.title,
                    tint = item.colorHex.toComposeColor(),
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
