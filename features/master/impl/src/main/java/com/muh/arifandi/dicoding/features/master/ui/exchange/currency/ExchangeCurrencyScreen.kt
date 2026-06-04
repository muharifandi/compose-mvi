package com.muh.arifandi.dicoding.features.master.ui.exchange.currency

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muh.arifandi.dicoding.core.ui.R
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaButton
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaCard
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaNavigationBar
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaScaffold
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme
import com.muh.arifandi.dicoding.features.master.ui.exchange.currency.state.*

@Composable
fun ExchangeCurrencyScreen(
    viewModel: ExchangeCurrencyViewModel = hiltViewModel(),
    onBackClick: () -> Unit = {}
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ExchangeCurrencyContent(
        state = state,
        onIntent = viewModel::processIntent,
        onBackClick = onBackClick
    )

    if (state.isFromCurrencyPickerOpen) {
        CurrencyPickerDialog(
            currencies = state.availableCurrencies,
            selectedCurrency = state.fromCurrency,
            onDismiss = { viewModel.processIntent(ExchangeCurrencyIntent.ToggleFromCurrencyPicker) },
            onSelect = { viewModel.processIntent(ExchangeCurrencyIntent.SelectFromCurrency(it)) }
        )
    }

    if (state.isToCurrencyPickerOpen) {
        CurrencyPickerDialog(
            currencies = state.availableCurrencies,
            selectedCurrency = state.toCurrency,
            onDismiss = { viewModel.processIntent(ExchangeCurrencyIntent.ToggleToCurrencyPicker) },
            onSelect = { viewModel.processIntent(ExchangeCurrencyIntent.SelectToCurrency(it)) }
        )
    }
}

@Composable
internal fun ExchangeCurrencyContent(
    state: ExchangeCurrencyState,
    onIntent: (ExchangeCurrencyIntent) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    SakaScaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color(0xFFF8F9FA),
        topBar = {
            SakaNavigationBar(
                title = "Exchange",
                onBackClick = onBackClick,
                backgroundColor = Color.White
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Illustration
            Image(
                painter = painterResource(id = R.drawable.ic_illustration_login),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(200.dp)
                    .padding(vertical = 24.dp),
                contentScale = ContentScale.Fit
            )

            SakaCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                shape = RoundedCornerShape(24.dp),
                isSmallShadow = true
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    // From Section
                    CurrencyInput(
                        label = "From",
                        amount = state.fromAmount,
                        currency = state.fromCurrency,
                        onAmountChange = { onIntent(ExchangeCurrencyIntent.FromAmountChanged(it)) },
                        onCurrencyClick = { onIntent(ExchangeCurrencyIntent.ToggleFromCurrencyPicker) }
                    )

                    // Swap Icon
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.SwapVert,
                                contentDescription = "Swap",
                                tint = Color(0xFF3629B7),
                                modifier = Modifier
                                    .size(32.dp)
                                    .clickable { onIntent(ExchangeCurrencyIntent.SwapCurrencies) }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            // Optional arrows in design
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowUp,
                                contentDescription = null,
                                tint = Color(0xFFFF4267),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }

                    // To Section
                    CurrencyInput(
                        label = "To",
                        amount = state.toAmount,
                        currency = state.toCurrency,
                        onAmountChange = {},
                        onCurrencyClick = { onIntent(ExchangeCurrencyIntent.ToggleToCurrencyPicker) },
                        readOnly = true
                    )

                    if (state.fromAmount.isNotEmpty()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Currency rate",
                                style = SakaTheme.typography.body3,
                                color = Color(0xFF3629B7),
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = "1 ${state.fromCurrency.code} = ${state.currencyRate.toInt()} ${state.toCurrency.code}",
                                style = SakaTheme.typography.body3,
                                color = Color(0xFF333333)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    SakaButton(
                        text = "Exchange",
                        onClick = { onIntent(ExchangeCurrencyIntent.SubmitExchange) },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = state.fromAmount.isNotEmpty()
                    )
                }
            }
        }
    }
}

@Composable
private fun CurrencyInput(
    label: String,
    amount: String,
    currency: CurrencyModel,
    onAmountChange: (String) -> Unit,
    onCurrencyClick: () -> Unit,
    readOnly: Boolean = false
) {
    Column {
        Text(
            text = label,
            style = SakaTheme.typography.caption2,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(12.dp))
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = amount,
                onValueChange = onAmountChange,
                modifier = Modifier.weight(1f),
                textStyle = SakaTheme.typography.body1.copy(fontSize = 18.sp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                readOnly = readOnly,
                decorationBox = { innerTextField ->
                    if (amount.isEmpty()) {
                        Text(
                            text = "Amount",
                            style = SakaTheme.typography.body1.copy(
                                color = Color.LightGray,
                                fontSize = 18.sp
                            )
                        )
                    }
                    innerTextField()
                }
            )
            VerticalDivider(
                modifier = Modifier
                    .padding(vertical = 12.dp, horizontal = 12.dp)
                    .width(1.dp),
                color = Color(0xFFE0E0E0)
            )
            Row(
                modifier = Modifier.clickable { onCurrencyClick() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = currency.code,
                    style = SakaTheme.typography.body1.copy(fontWeight = FontWeight.SemiBold),
                    color = Color(0xFF333333)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Color.Gray
                )
            }
        }
    }
}

@Composable
private fun CurrencyPickerDialog(
    currencies: List<CurrencyModel>,
    selectedCurrency: CurrencyModel,
    onDismiss: () -> Unit,
    onSelect: (CurrencyModel) -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(24.dp))
                    Text(
                        text = "Select the currency",
                        style = SakaTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                        color = Color(0xFF333333)
                    )
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.Gray)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn {
                    items(currencies) { currency ->
                        val isSelected = currency.code == selectedCurrency.code
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onSelect(currency) }
                                .padding(vertical = 12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${currency.code} ( ${currency.name} )",
                                style = SakaTheme.typography.body1,
                                color = if (isSelected) Color(0xFF3629B7) else Color.Gray,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                            if (isSelected) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = Color(0xFF3629B7),
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ExchangeCurrencyPreview() {
    MyApplicationTheme {
        ExchangeCurrencyContent(
            state = ExchangeCurrencyState(fromAmount = "1000"),
            onIntent = {},
            onBackClick = {}
        )
    }
}
