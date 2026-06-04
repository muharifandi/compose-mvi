package com.muh.arifandi.dicoding.features.master.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme
import com.muh.arifandi.dicoding.features.master.domain.model.MasterMenuItem
import com.muh.arifandi.dicoding.features.master.domain.model.MenuType
import com.muh.arifandi.dicoding.features.master.ui.component.HomeCardCarousel
import com.muh.arifandi.dicoding.features.master.ui.component.HomeHeader
import com.muh.arifandi.dicoding.features.master.ui.component.HomeMenuGrid
import com.muh.arifandi.dicoding.features.master.ui.home.state.*
import androidx.compose.ui.tooling.preview.Preview
import com.muh.arifandi.dicoding.features.master.domain.model.CreditCardInfo

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateByRoute: (String) -> Unit = {}
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is HomeEffect.NavigateToTransfer -> onNavigateByRoute("transfer_destination")
            }
        }
    }

    HomeScreenContent(
        state = state, 
        onIntent = viewModel::processIntent
    )
}

@Composable
private fun HomeScreenContent(
    state: HomeState,
    onIntent: (HomeIntent) -> Unit = {}
) {
    if (state.creditCards.isEmpty()) return
    
    val pagerState = rememberPagerState(pageCount = { state.creditCards.size })

    // Sync pager state with ViewModel
    LaunchedEffect(pagerState.currentPage) {
        onIntent(HomeIntent.SelectCard(pagerState.currentPage))
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val height = this.maxHeight
        val isSmallScreen = height < 640.dp
        
        val cardHeight = if (isSmallScreen) 185.dp else 215.dp
        val carouselHeight = if (isSmallScreen) 210.dp else 240.dp

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF2D229E))
        ) {
            HomeHeader(
                userName = "Push Puttichai",
                profileImageUrl = "https://i.pravatar.cc/150?u=arifandi",
                notificationCount = 3,
                isSmallScreen = isSmallScreen
            )

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
                    
                    HomeCardCarousel(
                        pagerState = pagerState,
                        creditCards = state.creditCards,
                        isDataVisible = state.isDataVisible,
                        onToggleVisibility = { onIntent(HomeIntent.ToggleDataVisibility) },
                        cardHeight = cardHeight,
                        carouselHeight = carouselHeight
                    )

                    HomeMenuGrid(
                        menuItems = state.homeMenuItems,
                        onMenuClick = { menuType ->
                            when (menuType) {
                                MenuType.TRANSFER -> onIntent(HomeIntent.NavigateToTransfer)
                                else -> {}
                            }
                        },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, device = "spec:width=360dp,height=640dp,dpi=480", name = "Standard")
@Composable
fun HomeScreenStandardPreview() {
    PreviewContent()
}

@Composable
private fun PreviewContent() {
    MyApplicationTheme {
        val dummyMenus = listOf(
            MasterMenuItem(MenuType.ACCOUNT, "Account", "#5E5CE6"),
            MasterMenuItem(MenuType.TRANSFER, "Transfer", "#FF2D55"),
            MasterMenuItem(MenuType.WITHDRAW, "Withdraw", "#007AFF"),
            MasterMenuItem(MenuType.MOBILE, "Mobile", "#FF9500"),
            MasterMenuItem(MenuType.BILLS, "Bills", "#34C759"),
            MasterMenuItem(MenuType.SAVINGS, "Savings", "#5856D6"),
        )
        
        HomeScreenContent(
            state = HomeState(
                creditCards = listOf(
                    CreditCardInfo("John Smith", "Amazon Platinium", "4756 •••• 9018", "$3,469", emptyList())
                ),
                homeMenuItems = dummyMenus
            )
        )
    }
}
