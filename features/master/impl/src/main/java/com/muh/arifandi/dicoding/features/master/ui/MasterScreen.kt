/**
 * Created by Muh. Arifandi on 25/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:master:impl
 * File : MasterScreen.kt
 *
 * Description:
 * Container utama aplikasi yang mengelola navigasi Bottom Bar (Home, Search, Message, Settings).
 * Mengimplementasikan desain dashboard perbankan sesuai permintaan user.
 */

package com.muh.arifandi.dicoding.features.master.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.CompareArrows
import androidx.compose.material.icons.automirrored.filled.ReceiptLong
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muh.arifandi.dicoding.core.ui.designsystem.components.*
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme
import com.muh.arifandi.dicoding.features.master.ui.state.MasterIntent
import com.muh.arifandi.dicoding.features.master.ui.state.MasterState
import com.muh.arifandi.dicoding.features.master.ui.state.MasterTab

@Composable
fun MasterScreen(
    viewModel: MasterViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    MasterContent(
        state = state,
        onIntent = { viewModel.processIntent(it) }
    )
}

@Composable
internal fun MasterContent(
    state: MasterState,
    onIntent: (MasterIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        SakaTabItem("Home", Icons.Outlined.Home),
        SakaTabItem("Search", Icons.Outlined.Search),
        SakaTabItem("Message", Icons.Outlined.Email),
        SakaTabItem("Settings", Icons.Outlined.Settings)
    )

    SakaScaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            SakaTabBar(
                items = items,
                selectedIndex = state.selectedTab.ordinal,
                onItemSelected = { index ->
                    onIntent(MasterIntent.SelectTab(MasterTab.entries[index]))
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (state.selectedTab) {
                MasterTab.HOME -> HomeContent()
                MasterTab.SEARCH -> PlaceholderContent("Search Screen")
                MasterTab.MESSAGE -> PlaceholderContent("Message Screen")
                MasterTab.SETTINGS -> PlaceholderContent("Settings Screen")
            }
        }
    }
}

@Composable
private fun HomeContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SakaTheme.colors.neutralWhite)
    ) {
        // 1. Header (Blue Background)
        HeaderSection()

        // 2. Main Container with White Background and Card
        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = (-40).dp) // Offset to overlap the blue header
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            ) {
                // Credit Card
                CreditCardSection()

                Spacer(modifier = Modifier.height(24.dp))

                // Menu Grid
                MenuGridSection()
            }
        }
    }
}

@Composable
private fun HeaderSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(SakaTheme.colors.primary, SakaTheme.colors.primaryLight)
                )
            )
            .padding(horizontal = 24.dp, vertical = 40.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profile Pic
            SakaAsyncImage(
                model = "https://i.pravatar.cc/150?u=arifandi", // Placeholder image
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Greeting
            Text(
                text = "Hi, Push Puttichai",
                style = SakaTheme.typography.title3,
                color = SakaTheme.colors.neutralWhite
            )

            Spacer(modifier = Modifier.weight(1f))

            // Notification Bell
            BadgedBox(
                badge = {
                    Badge(
                        containerColor = SakaTheme.colors.semanticError,
                        contentColor = SakaTheme.colors.neutralWhite
                    ) {
                        Text("3")
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notifications",
                    tint = SakaTheme.colors.neutralWhite,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}

@Composable
private fun CreditCardSection() {
    SakaCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        backgroundColor = SakaTheme.colors.primary, // Using primary for the dark blue look
        shape = RoundedCornerShape(24.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFF1A1F71), Color(0xFF281C9D))
                    )
                )
                .padding(24.dp)
        ) {
            // Visual accent circles (like in the image)
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .offset(x = 180.dp, y = (-50).dp)
                    .clip(CircleShape)
                    .background(SakaTheme.colors.primaryLight.copy(alpha = 0.3f))
            )

            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "John Smith",
                    style = SakaTheme.typography.title1,
                    color = Color.White
                )
                
                Text(
                    text = "Amazon Platinum",
                    style = SakaTheme.typography.body3,
                    color = SakaTheme.colors.neutralPlatinum,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "4756 •••• •••• 9018",
                        style = SakaTheme.typography.body2,
                        color = Color.White,
                        letterSpacing = 2.sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$3,469.52",
                        style = SakaTheme.typography.title2,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "VISA",
                        style = SakaTheme.typography.title1,
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        }
    }
}

data class MenuItem(val title: String, val icon: ImageVector, val color: Color)

@Composable
private fun MenuGridSection() {
    val menuItems = listOf(
        MenuItem("Account and Card", Icons.Default.Wallet, Color(0xFF6C5CE7)),
        MenuItem("Transfer", Icons.AutoMirrored.Filled.CompareArrows, Color(0xFFFF7675)),
        MenuItem("Withdraw", Icons.Default.Atm, Color(0xFF0984E3)),
        MenuItem("Mobile prepaid", Icons.Default.Smartphone, Color(0xFFFDCB6E)),
        MenuItem("Pay the bill", Icons.AutoMirrored.Filled.ReceiptLong, Color(0xFF00B894)),
        MenuItem("Save online", Icons.Default.Savings, Color(0xFF6C5CE7)),
        MenuItem("Credit card", Icons.Default.CreditCard, Color(0xFFE17055)),
        MenuItem("Transaction report", Icons.Default.Assessment, Color(0xFF0984E3)),
        MenuItem("Beneficiary", Icons.Default.ContactPage, Color(0xFFD63031))
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        items(menuItems) { item ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                SakaCard(
                    modifier = Modifier.size(64.dp),
                    shape = RoundedCornerShape(16.dp),
                    backgroundColor = SakaTheme.colors.primarySubtle
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
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = item.title,
                    style = SakaTheme.typography.caption2,
                    color = SakaTheme.colors.neutralGrey,
                    textAlign = TextAlign.Center,
                    lineHeight = 16.sp
                )
            }
        }
    }
}

@Composable
private fun PlaceholderContent(text: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, style = SakaTheme.typography.title2)
    }
}

@Preview(showBackground = true)
@Composable
private fun MasterScreenPreview() {
    MyApplicationTheme {
        MasterContent(
            state = MasterState(),
            onIntent = {}
        )
    }
}
