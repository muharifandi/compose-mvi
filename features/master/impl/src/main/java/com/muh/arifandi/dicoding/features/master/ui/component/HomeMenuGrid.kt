package com.muh.arifandi.dicoding.features.master.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.muh.arifandi.dicoding.features.master.domain.model.MasterMenuItem
import com.muh.arifandi.dicoding.features.master.domain.model.MenuType

@Composable
fun HomeMenuGrid(
    menuItems: List<MasterMenuItem>,
    onMenuClick: (MenuType) -> Unit,
    modifier: Modifier = Modifier
) {
    // Logic: Limit to 9 items. If total > 9, the 9th item is "More"
    val displayItems = if (menuItems.size > 9) {
        menuItems.take(8) + MasterMenuItem(MenuType.MORE, "More", "#8E8E93")
    } else {
        menuItems.take(9)
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            val rows = displayItems.chunked(3)
            rows.forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    rowItems.forEach { item ->
                        Box(modifier = Modifier.width(100.dp)) {
                            MasterMenuGridItem(
                                item = item,
                                onClick = { onMenuClick(item.type) }
                            )
                        }
                    }
                    repeat(3 - rowItems.size) {
                        Spacer(modifier = Modifier.width(100.dp))
                    }
                }
            }
        }
    }
}
