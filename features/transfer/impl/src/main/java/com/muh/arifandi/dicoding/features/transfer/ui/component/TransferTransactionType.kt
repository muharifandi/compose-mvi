package com.muh.arifandi.dicoding.features.transfer.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme
import com.muh.arifandi.dicoding.features.transfer.ui.state.TransactionType

@Composable
fun TransferTransactionTypeSelector(
    selectedType: TransactionType,
    onTypeSelect: (TransactionType) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TransactionTypeCard(
            title = "Transfer via card number",
            icon = Icons.Default.CreditCard,
            isSelected = selectedType == TransactionType.CARD,
            onClick = { onTypeSelect(TransactionType.CARD) },
            modifier = Modifier.weight(1f)
        )
        TransactionTypeCard(
            title = "Transfer to the same bank",
            icon = Icons.Default.Person,
            isSelected = selectedType == TransactionType.SAME_BANK,
            onClick = { onTypeSelect(TransactionType.SAME_BANK) },
            modifier = Modifier.weight(1f)
        )
        TransactionTypeCard(
            title = "Transfer to another bank",
            icon = Icons.Default.AccountBalance,
            isSelected = selectedType == TransactionType.ANOTHER_BANK,
            onClick = { onTypeSelect(TransactionType.ANOTHER_BANK) },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun TransactionTypeCard(
    title: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (isSelected) Color(0xFFFEAF3A) else Color(0xFFE5E5EA)
    val contentColor = if (isSelected) Color.White else Color.Gray

    Column(
        modifier = modifier
            .height(110.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .clickable { onClick() }
            .padding(12.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = contentColor,
            modifier = Modifier.size(28.dp)
        )
        Text(
            text = title,
            style = SakaTheme.typography.caption2.copy(lineHeight = 14.sp),
            color = contentColor,
            fontWeight = FontWeight.Medium
        )
    }
}
