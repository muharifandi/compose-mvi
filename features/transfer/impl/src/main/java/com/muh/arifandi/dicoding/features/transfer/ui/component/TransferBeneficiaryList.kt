package com.muh.arifandi.dicoding.features.transfer.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaAsyncImage
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaCard
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme
import com.muh.arifandi.dicoding.features.transfer.domain.model.Beneficiary

@Composable
fun TransferBeneficiaryList(
    beneficiaries: List<Beneficiary>,
    onBeneficiaryClick: (Beneficiary) -> Unit,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(end = 16.dp)
    ) {
        item {
            AddBeneficiaryButton(onClick = onAddClick)
        }
        items(beneficiaries) { beneficiary ->
            BeneficiaryItem(
                beneficiary = beneficiary,
                onClick = { onBeneficiaryClick(beneficiary) }
            )
        }
    }
}

@Composable
private fun AddBeneficiaryButton(onClick: () -> Unit) {
    SakaCard(
        modifier = Modifier.size(100.dp, 120.dp).clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        isSmallShadow = true,
        backgroundColor = Color.White
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF2F1F9)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = Color(0xFFD7D4EF),
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}

@Composable
private fun BeneficiaryItem(beneficiary: Beneficiary, onClick: () -> Unit) {
    SakaCard(
        modifier = Modifier
            .size(100.dp, 120.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        isSmallShadow = true,
        backgroundColor = Color.White
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize().padding(8.dp)
        ) {
            SakaAsyncImage(
                model = beneficiary.imageUrl,
                modifier = Modifier
                    .size(54.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = beneficiary.name,
                style = SakaTheme.typography.body3.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                ),
                color = Color(0xFF333333),
                maxLines = 1
            )
        }
    }
}
