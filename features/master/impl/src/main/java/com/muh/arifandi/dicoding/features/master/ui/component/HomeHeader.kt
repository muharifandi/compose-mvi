package com.muh.arifandi.dicoding.features.master.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
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
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaAsyncImage
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme

@Composable
fun HomeHeader(
    userName: String,
    profileImageUrl: String,
    notificationCount: Int,
    isSmallScreen: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = if (isSmallScreen) 10.dp else 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SakaAsyncImage(
                model = profileImageUrl,
                modifier = Modifier
                    .size(if (isSmallScreen) 38.dp else 45.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "Hi, $userName",
                style = SakaTheme.typography.title3,
                color = SakaTheme.colors.neutralWhite,
                fontWeight = FontWeight.SemiBold,
                fontSize = if (isSmallScreen) 16.sp else 18.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            BadgedBox(
                badge = {
                    if (notificationCount > 0) {
                        Badge(
                            containerColor = Color(0xFFFF4757),
                            contentColor = SakaTheme.colors.neutralWhite,
                            modifier = Modifier.offset(x = (-4).dp, y = 4.dp)
                        ) {
                            Text(notificationCount.toString())
                        }
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notifications",
                    tint = SakaTheme.colors.neutralWhite,
                    modifier = Modifier.size(26.dp)
                )
            }
        }
    }
}
