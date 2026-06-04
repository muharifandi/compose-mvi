package com.muh.arifandi.dicoding.features.master.ui.message

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaCard
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaNavigationBar
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaScaffold
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme
import com.muh.arifandi.dicoding.features.master.domain.model.MessageModel
import com.muh.arifandi.dicoding.features.master.ui.message.state.MessageState

@Composable
fun MessageScreen(
    viewModel: MessageViewModel = hiltViewModel(),
    onBackClick: () -> Unit = {}
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    MessageScreenContent(
        state = state,
        onBackClick = onBackClick
    )
}

@Composable
private fun MessageScreenContent(
    state: MessageState,
    onBackClick: () -> Unit = {}
) {
    SakaScaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFF8F9FA),
        topBar = {
            SakaNavigationBar(
                title = "Message",
                onBackClick = onBackClick,
                backgroundColor = Color.White
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.messages) { message ->
                MessageItem(message = message)
            }
        }
    }
}

@Composable
private fun MessageItem(message: MessageModel) {
    SakaCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        isSmallShadow = true,
        backgroundColor = Color.White
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(message.iconBackgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = getIconForSender(message.sender),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = message.sender,
                        style = SakaTheme.typography.body2.copy(fontWeight = FontWeight.Bold),
                        color = Color(0xFF333333)
                    )
                    Text(
                        text = message.date,
                        style = SakaTheme.typography.caption2,
                        color = Color.LightGray
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = message.summary,
                    style = SakaTheme.typography.caption2.copy(lineHeight = 16.sp),
                    color = Color.Gray,
                    maxLines = 1
                )
            }
        }
    }
}

private fun getIconForSender(sender: String): ImageVector {
    return when (sender) {
        "Bank of America" -> Icons.Default.AccountBalance
        "Account" -> Icons.Default.Person
        "Alert" -> Icons.Default.ChatBubble
        "Paypal" -> Icons.Default.Payment
        "Withdraw" -> Icons.Default.CreditCard
        else -> Icons.Default.Mail
    }
}

@Preview(showBackground = true)
@Composable
private fun MessageScreenPreview() {
    MyApplicationTheme {
        MessageScreenContent(
            state = MessageState(
                messages = listOf(
                    MessageModel("1", "Bank of America", "Bank of America: 256486 is the au...", "Today", Color(0xFF3629B7)),
                    MessageModel("2", "Account", "Your account is limited. Please foll...", "12/10", Color(0xFFFF4B6E))
                )
            )
        )
    }
}
