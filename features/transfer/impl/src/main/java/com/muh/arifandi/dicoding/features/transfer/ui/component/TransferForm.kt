package com.muh.arifandi.dicoding.features.transfer.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaButton
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaButtonType
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaCard
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaTextField
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme

@Composable
fun TransferForm(
    selectedBank: String,
    selectedBranch: String,
    name: String,
    cardNumber: String,
    amount: String,
    note: String,
    saveToDirectory: Boolean,
    onBankClick: () -> Unit,
    onBranchClick: () -> Unit,
    onNameChange: (String) -> Unit,
    onCardNumberChange: (String) -> Unit,
    onAmountChange: (String) -> Unit,
    onNoteChange: (String) -> Unit,
    onSaveToDirectoryToggle: (Boolean) -> Unit,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier
) {
    SakaCard(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        isSmallShadow = true,
        backgroundColor = Color.White
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Box(modifier = Modifier.fillMaxWidth()) {
                SakaTextField(
                    value = selectedBank,
                    onValueChange = {},
                    placeholder = "Choose bank",
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null,
                            tint = Color.LightGray
                        )
                    }
                )
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .clickable { onBankClick() }
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                SakaTextField(
                    value = selectedBranch,
                    onValueChange = {},
                    placeholder = "Choose branch",
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null,
                            tint = Color.LightGray
                        )
                    }
                )
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .clickable { onBranchClick() }
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            SakaTextField(
                value = name,
                onValueChange = onNameChange,
                placeholder = "Name",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            SakaTextField(
                value = cardNumber,
                onValueChange = onCardNumberChange,
                placeholder = "Card number",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            SakaTextField(
                value = amount,
                onValueChange = onAmountChange,
                placeholder = "Amount",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            SakaTextField(
                value = note,
                onValueChange = onNoteChange,
                placeholder = "Note",
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = saveToDirectory,
                    onCheckedChange = onSaveToDirectoryToggle,
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF3629B7),
                        uncheckedColor = Color.LightGray
                    )
                )
                Text(
                    text = "Save to directory of beneficiary",
                    style = SakaTheme.typography.caption2,
                    color = Color.LightGray
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
            SakaButton(
                text = "Confirm",
                onClick = onSubmit,
                modifier = Modifier.fillMaxWidth(),
                enabled = amount.isNotEmpty() && cardNumber.isNotEmpty() && selectedBank.isNotEmpty(),
                type = SakaButtonType.PRIMARY
            )
        }
    }
}
