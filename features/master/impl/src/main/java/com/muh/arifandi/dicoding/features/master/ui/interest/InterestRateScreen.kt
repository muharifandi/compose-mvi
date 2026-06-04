package com.muh.arifandi.dicoding.features.master.ui.interest

import androidx.compose.foundation.background
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaNavigationBar
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaScaffold
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme
import com.muh.arifandi.dicoding.features.master.domain.model.InterestRateItem
import com.muh.arifandi.dicoding.features.master.ui.component.InterestRateRow
import com.muh.arifandi.dicoding.features.master.ui.interest.state.InterestRateState

@Composable
fun InterestRateScreen(
    viewModel: InterestRateViewModel = hiltViewModel(),
    onBackClick: () -> Unit = {}
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    InterestRateContent(
        state = state,
        onBackClick = onBackClick
    )
}

@Composable
internal fun InterestRateContent(
    state: InterestRateState,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    SakaScaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color.White,
        topBar = {
            SakaNavigationBar(
                title = "Interest rate",
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
                    text = "Interest kind",
                    style = SakaTheme.typography.body3,
                    color = Color(0xFFAAAAAA),
                    modifier = Modifier.weight(1.5f)
                )
                Text(
                    text = "Deposit",
                    style = SakaTheme.typography.body3,
                    color = Color(0xFFAAAAAA),
                    modifier = Modifier.weight(0.7f),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Rate",
                    style = SakaTheme.typography.body3,
                    color = Color(0xFFAAAAAA),
                    modifier = Modifier.weight(0.5f),
                    textAlign = TextAlign.End
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.rates) { item ->
                    InterestRateRow(item)
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 8.dp),
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
private fun InterestRatePreview() {
    MyApplicationTheme {
        InterestRateContent(
            state = InterestRateState(
                rates = listOf(
                    InterestRateItem("Individual customers", "1m", "4.50%", isPrimary = true),
                    InterestRateItem("Corporate customers", "2m", "5.50%"),
                    InterestRateItem("Individual customers", "1m", "4.50%", isPrimary = true)
                )
            ),
            onBackClick = {}
        )
    }
}
