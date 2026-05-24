package com.muh.arifandi.dicoding.features.master.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.CompareArrows
import androidx.compose.material.icons.automirrored.filled.ReceiptLong
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaAsyncImage
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaCreditCard
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme
import com.muh.arifandi.dicoding.features.master.domain.model.CreditCardInfo
import com.muh.arifandi.dicoding.features.master.domain.model.MasterMenuItem
import com.muh.arifandi.dicoding.features.master.ui.MasterViewModel
import com.muh.arifandi.dicoding.features.master.ui.component.MasterMenuGridItem
import com.muh.arifandi.dicoding.features.master.ui.state.MasterIntent
import com.muh.arifandi.dicoding.features.master.ui.state.MasterState
import androidx.compose.ui.tooling.preview.Preview
import kotlin.math.absoluteValue
/**
 * Created by Muh. Arifandi on 25/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:master:impl
 * File : CreditCardInfo.kt
 */

@Composable
fun HomeScreen(
    viewModel: MasterViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeScreenContent(state = state, onIntent = viewModel::processIntent)
}

@Composable
private fun HomeScreenContent(
    state: MasterState,
    onIntent: (MasterIntent) -> Unit = {}
) {
    if (state.creditCards.isEmpty()) return
    
    val pagerState = rememberPagerState(pageCount = { state.creditCards.size })
    var isDataVisible by remember { mutableStateOf(false) }

    // Sync pager state with ViewModel
    LaunchedEffect(pagerState.currentPage) {
        onIntent(MasterIntent.SelectCard(pagerState.currentPage))
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        // Explicitly using the scope properties to avoid IDE warnings
        val height = this.maxHeight
        val isSmallScreen = height < 640.dp
        
        // Dynamic scaling based on detected screen height
        val cardHeight = if (isSmallScreen) 185.dp else 215.dp
        val carouselHeight = if (isSmallScreen) 210.dp else 240.dp

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF2D229E))
        ) {
            HeaderSection(isSmallScreen)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                    .background(SakaTheme.colors.neutralWhite)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(if (isSmallScreen) 12.dp else 20.dp))
                    
                    // Card Carousel Section (Adaptive Height)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(carouselHeight),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        VerticalPager(
                            state = pagerState,
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(bottom = 20.dp),
                            beyondViewportPageCount = 2
                        ) { page ->
                            val pageOffset = (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                            val card = state.creditCards[page]
                            
                            SakaCreditCard(
                                holderName = card.holderName,
                                cardType = card.cardType,
                                cardNumber = card.cardNumber,
                                balance = card.balance,
                                gradientColors = card.gradientColors,
                                backgroundModel = card.backgroundRes,
                                isVisible = isDataVisible,
                                onToggleVisibility = { isDataVisible = !isDataVisible },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                                    .height(cardHeight)
                                    .zIndex(10f - page)
                                    .graphicsLayer {
                                        if (pageOffset > 0) {
                                            translationY = -pageOffset * size.height
                                            alpha = (1f - pageOffset).coerceIn(0f, 1f)
                                        } else {
                                            val absOffset = pageOffset.absoluteValue
                                            scaleX = 1f - (absOffset * 0.05f)
                                            scaleY = 1f - (absOffset * 0.02f)
                                            translationY = (-absOffset * size.height) + (absOffset * 10.dp.toPx())
                                            alpha = 1f
                                        }
                                        transformOrigin = androidx.compose.ui.graphics.TransformOrigin(0.5f, 0f)
                                    }
                            )
                        }
                    }

                    // Adaptive Menu Section (Takes remaining space and centers items)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(start = 16.dp, end = 16.dp, bottom = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        MenuGridSection(menuItems = state.homeMenuItems)
                    }
                }
            }
        }
    }
}

@Composable
private fun HeaderSection(isSmallScreen: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = if (isSmallScreen) 10.dp else 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SakaAsyncImage(
                model = "https://i.pravatar.cc/150?u=arifandi",
                modifier = Modifier
                    .size(if (isSmallScreen) 38.dp else 45.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "Hi, Push Puttichai",
                style = SakaTheme.typography.title3,
                color = SakaTheme.colors.neutralWhite,
                fontWeight = FontWeight.SemiBold,
                fontSize = if (isSmallScreen) 16.sp else 18.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            BadgedBox(
                badge = {
                    Badge(
                        containerColor = Color(0xFFFF4757),
                        contentColor = SakaTheme.colors.neutralWhite,
                        modifier = Modifier.offset(x = (-4).dp, y = 4.dp)
                    ) {
                        Text("3")
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

@Composable
private fun MenuGridSection(menuItems: List<MasterMenuItem>) {
    // Logic: Limit to 9 items. If total > 9, the 9th item is "More"
    val displayItems = if (menuItems.size > 9) {
        menuItems.take(8) + MasterMenuItem("More", Icons.Default.MoreHoriz, Color(0xFF8E8E93))
    } else {
        menuItems.take(9)
    }

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
                        MasterMenuGridItem(item = item)
                    }
                }
                repeat(3 - rowItems.size) {
                    Spacer(modifier = Modifier.width(100.dp))
                }
            }
        }
    }
}

// Previews for scalability check
@Preview(showBackground = true, device = "spec:width=320dp,height=480dp,dpi=320", name = "Small (4.5 inch)")
@Composable
fun HomeScreenSmallPreview() {
    PreviewContent()
}

@Preview(showBackground = true, device = "spec:width=360dp,height=640dp,dpi=480", name = "Standard (5 inch)")
@Composable
fun HomeScreenStandardPreview() {
    PreviewContent()
}

@Preview(showBackground = true, device = "spec:width=600dp,height=1024dp,dpi=240", name = "Large (7 inch)")
@Composable
fun HomeScreenLargePreview() {
    PreviewContent()
}

@Composable
private fun PreviewContent() {
    MyApplicationTheme {
        val dummyMenus = listOf(
            MasterMenuItem("Account", Icons.Default.AccountBalanceWallet, Color(0xFF5E5CE6)),
            MasterMenuItem("Transfer", Icons.AutoMirrored.Filled.CompareArrows, Color(0xFFFF2D55)),
            MasterMenuItem("Withdraw", Icons.Default.Atm, Color(0xFF007AFF)),
            MasterMenuItem("Mobile", Icons.Default.Smartphone, Color(0xFFFF9500)),
            MasterMenuItem("Bills", Icons.AutoMirrored.Filled.ReceiptLong, Color(0xFF34C759)),
            MasterMenuItem("Savings", Icons.Default.Savings, Color(0xFF5856D6)),
            MasterMenuItem("Credit", Icons.Default.CreditCard, Color(0xFFFF2D55)),
            MasterMenuItem("QR", Icons.Default.QrCodeScanner, Color(0xFF007AFF)),
            MasterMenuItem("Top Up", Icons.Default.AddCircle, Color(0xFF34C759)),
            MasterMenuItem("Extra", Icons.Default.History, Color(0xFF5856D6)),
        )
        
        HomeScreenContent(
            state = MasterState(
                creditCards = listOf(
                    CreditCardInfo("John Smith", "Amazon Platinium", "4756 •••• 9018", "$3,469", emptyList())
                ),
                homeMenuItems = dummyMenus
            )
        )
    }
}
