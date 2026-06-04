package com.muh.arifandi.dicoding.features.master.ui.exchange

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaNavigationBar
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaScaffold
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme
import com.muh.arifandi.dicoding.features.master.domain.model.ExchangeRateItem
import com.muh.arifandi.dicoding.features.master.ui.component.ExchangeRateRow
import com.muh.arifandi.dicoding.features.master.ui.exchange.state.ExchangeRateState

@Composable
fun ExchangeRateScreen(
    viewModel: ExchangeRateViewModel = hiltViewModel(),
    onBackClick: () -> Unit = {}
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ExchangeRateContent(
        state = state,
        onBackClick = onBackClick
    )
}

@Composable
internal fun ExchangeRateContent(
    state: ExchangeRateState,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    SakaScaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color.White,
        topBar = {
            SakaNavigationBar(
                title = "Exchange rate",
                onBackClick = onBackClick,
                backgroundColor = Color.White
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Header Table
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Country",
                    style = SakaTheme.typography.body3,
                    color = Color(0xFFAAAAAA),
                    modifier = Modifier.weight(1.5f)
                )
                Text(
                    text = "Buy",
                    style = SakaTheme.typography.body3,
                    color = Color(0xFFAAAAAA),
                    modifier = Modifier.weight(0.7f),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Sell",
                    style = SakaTheme.typography.body3,
                    color = Color(0xFFAAAAAA),
                    modifier = Modifier.weight(0.7f),
                    textAlign = TextAlign.End
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.rates) { item ->
                    ExchangeRateRow(item)
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        thickness = 1.dp,
                        color = Color(0xFFF2F2F7)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ExchangeRatePreview() {
    MyApplicationTheme {
        ExchangeRateContent(
            state = ExchangeRateState(
                rates = listOf(
                    ExchangeRateItem(
                        "Vietnam",
                        "https://flagcdn.com/w320/vn.png",
                        "1.403",
                        "1.746"
                    ),
                    ExchangeRateItem("Nicaragua", "https://flagcdn.com/w320/ni.png", "9.123", "12.09")
                )
            ),
            onBackClick = {}
        )
    }
}
