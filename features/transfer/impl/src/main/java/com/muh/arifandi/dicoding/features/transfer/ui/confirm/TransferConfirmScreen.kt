package com.muh.arifandi.dicoding.features.transfer.ui.confirm

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muh.arifandi.dicoding.core.ui.designsystem.components.*
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme
import com.muh.arifandi.dicoding.features.transfer.ui.confirm.state.TransferConfirmEffect
import com.muh.arifandi.dicoding.features.transfer.ui.confirm.state.TransferConfirmIntent
import com.muh.arifandi.dicoding.features.transfer.ui.confirm.state.TransferConfirmState
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaButtonType

@Composable
fun TransferConfirmScreen(
    viewModel: TransferConfirmViewModel = hiltViewModel(),
    onBackClick: () -> Unit = {},
    onNavigateToHome: () -> Unit = {}
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is TransferConfirmEffect.NavigateToSuccess -> { /* Handled via state.isSuccess */ }
                is TransferConfirmEffect.ShowError -> { /* Show snackbar */ }
            }
        }
    }

    if (state.isSuccess) {
        TransferSuccessContent(
            amount = state.amount,
            toName = state.toName,
            onConfirm = onNavigateToHome
        )
    } else {
        TransferConfirmContent(
            state = state,
            onIntent = viewModel::processIntent,
            onBackClick = onBackClick
        )
    }
}

@Composable
internal fun TransferConfirmContent(
    state: TransferConfirmState,
    onIntent: (TransferConfirmIntent) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    if (state.showBiometricDialog) {
        BiometricDialog(
            onDismiss = { onIntent(TransferConfirmIntent.DismissBiometric) },
            onConfirm = { 
                onIntent(TransferConfirmIntent.DismissBiometric)
                onIntent(TransferConfirmIntent.ConfirmTransfer)
            }
        )
    }

    SakaScaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color.White,
        topBar = {
            SakaNavigationBar(
                title = "Confirm",
                onBackClick = onBackClick,
                backgroundColor = Color.White
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Confirm transaction information",
                style = SakaTheme.typography.caption1,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(24.dp))

            ConfirmInfoItem(label = "From", value = state.fromAccount)
            ConfirmInfoItem(label = "To", value = state.toName)
            ConfirmInfoItem(label = "Beneficiary bank", value = state.beneficiaryBank)
            ConfirmInfoItem(label = "Card number", value = state.cardNumber)
            ConfirmInfoItem(label = "Transaction fee", value = state.fee)
            ConfirmInfoItem(label = "Content", value = state.content)
            ConfirmInfoItem(label = "Amount", value = state.amount)

            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Get OTP to verify transaction",
                style = SakaTheme.typography.caption1,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                SakaTextField(
                    value = state.otp,
                    onValueChange = { onIntent(TransferConfirmIntent.OtpChanged(it)) },
                    placeholder = "OTP",
                    modifier = Modifier.weight(1f)
                )
                SakaButton(
                    text = "Get OTP",
                    onClick = { onIntent(TransferConfirmIntent.RequestOtp) },
                    modifier = Modifier.width(110.dp),
                    type = if (state.isOtpSent) SakaButtonType.NEUTRAL else SakaButtonType.PRIMARY
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
            
            // Face ID / Biometric UI
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Use Face ID to verify transaction",
                    style = SakaTheme.typography.caption1,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Icon(
                    imageVector = Icons.Default.Face,
                    contentDescription = "Face ID",
                    tint = Color(0xFF3629B7),
                    modifier = Modifier
                        .size(80.dp)
                        .clickable { onIntent(TransferConfirmIntent.BiometricAuth) }
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            SakaButton(
                text = "Confirm",
                onClick = { onIntent(TransferConfirmIntent.ConfirmTransfer) },
                modifier = Modifier.fillMaxWidth(),
                enabled = state.otp.isNotEmpty() && !state.isLoading,
                isLoading = state.isLoading
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun TransferSuccessContent(
    amount: String,
    toName: String,
    onConfirm: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Mock Illustration
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("💸", fontSize = 100.sp) // Representing the illustration from Image 5
        }

        Spacer(modifier = Modifier.height(32.dp))
        
        Text(
            text = "Transfer successful!",
            style = SakaTheme.typography.title2,
            color = Color(0xFF3629B7),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = buildAnnotatedString {
                append("You have successfully transferred ")
                withStyle(style = SpanStyle(color = Color(0xFFFF4757), fontWeight = FontWeight.Bold)) {
                    append(amount)
                }
                append(" to ")
                withStyle(style = SpanStyle(color = Color(0xFF3629B7), fontWeight = FontWeight.Bold)) {
                    append(toName)
                }
                append("!")
            },
            style = SakaTheme.typography.body2,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(48.dp))

        SakaButton(
            text = "Confirm",
            onClick = onConfirm,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun BiometricDialog(onDismiss: () -> Unit, onConfirm: () -> Unit) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = true)
    ) {
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.95f)),
            modifier = Modifier.width(280.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFFB2C1)), // Mocking the pink circle in Image 1
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Fingerprint,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "Touch ID for CoBank",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                
                Text(
                    text = "Access to the application",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                HorizontalDivider(color = Color.LightGray)
                
                TextButton(
                    onClick = onConfirm,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Confirm", color = Color(0xFF007AFF), fontWeight = FontWeight.Bold)
                }
                
                HorizontalDivider(color = Color.LightGray)
                
                TextButton(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Cancel", color = Color(0xFF007AFF))
                }
            }
        }
    }
}

@Composable
private fun ConfirmInfoItem(label: String, value: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)) {
        Text(
            text = label,
            style = SakaTheme.typography.caption2,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        SakaTextField(
            value = value,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
