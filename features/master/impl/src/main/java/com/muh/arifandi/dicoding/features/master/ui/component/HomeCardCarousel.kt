package com.muh.arifandi.dicoding.features.master.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaCreditCard
import com.muh.arifandi.dicoding.features.master.domain.model.CreditCardInfo
import com.muh.arifandi.dicoding.features.master.ui.mapper.toComposeColor
import kotlin.math.absoluteValue

@Composable
fun HomeCardCarousel(
    pagerState: PagerState,
    creditCards: List<CreditCardInfo>,
    isDataVisible: Boolean,
    onToggleVisibility: () -> Unit,
    cardHeight: Dp,
    carouselHeight: Dp,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
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
            val card = creditCards[page]
            
            SakaCreditCard(
                holderName = card.holderName,
                cardType = card.cardType,
                cardNumber = card.cardNumber,
                balance = card.balance,
                gradientColors = card.gradientColorsHex.map { it.toComposeColor() },
                backgroundModel = card.backgroundRes,
                isVisible = isDataVisible,
                onToggleVisibility = onToggleVisibility,
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
}
