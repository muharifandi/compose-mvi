/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : core:ui
 * File : SakaTextField.kt
 *
 * Description:
 * Komponen Input Teks kustom untuk Saka Design System, termasuk Password, Search, dan Currency field.
 */

package com.muh.arifandi.dicoding.core.ui.designsystem.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.UnfoldMore
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme

/**
 * Komponen Input Teks kustom untuk Saka Design System.
 *
 * @param value Nilai teks saat ini.
 * @param onValueChange Callback saat teks berubah.
 * @param modifier Modifier untuk kustomisasi layout.
 * @param label Teks label di atas input (opsional).
 * @param placeholder Teks petunjuk di dalam input (opsional).
 * @param helperText Teks bantuan di bawah input (opsional).
 * @param isRequired Jika true, akan menampilkan tanda (*) pada label.
 * @param isError Menandakan apakah input dalam kondisi error.
 * @param errorMessage Pesan kesalahan yang ditampilkan jika isError true.
 */
@Composable
fun SakaTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    helperText: String? = null,
    isRequired: Boolean = false,
    isError: Boolean = false,
    errorMessage: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    singleLine: Boolean = true,
    minLines: Int = 1,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    enabled: Boolean = true,
    readOnly: Boolean = false
) {
    Column(modifier = modifier.fillMaxWidth()) {
        label?.let {
            Row {
                Text(
                    text = it,
                    style = SakaTheme.typography.caption1,
                    color = SakaTheme.colors.neutralGrey
                )
                if (isRequired) {
                    Text(
                        text = " *",
                        style = SakaTheme.typography.caption1,
                        color = SakaTheme.colors.semanticError
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            readOnly = readOnly,
            placeholder = placeholder?.let {
                {
                    Text(
                        text = it,
                        style = SakaTheme.typography.body2,
                        color = SakaTheme.colors.neutralGrey
                    )
                }
            },
            isError = isError,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            suffix = suffix,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            singleLine = singleLine,
            minLines = minLines,
            maxLines = maxLines,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = SakaTheme.colors.primary,
                unfocusedBorderColor = SakaTheme.colors.neutralSilver,
                errorBorderColor = SakaTheme.colors.semanticError,
                focusedLabelColor = SakaTheme.colors.primary,
                unfocusedLabelColor = SakaTheme.colors.neutralGrey,
                cursorColor = SakaTheme.colors.primary,
                disabledBorderColor = SakaTheme.colors.neutralPlatinum,
                disabledTextColor = SakaTheme.colors.neutralGrey,
                focusedPlaceholderColor = SakaTheme.colors.neutralGrey,
                unfocusedPlaceholderColor = SakaTheme.colors.neutralGrey
            ),
            textStyle = SakaTheme.typography.body2
        )

        val bottomText = if (isError) errorMessage else helperText
        val bottomColor = if (isError) SakaTheme.colors.semanticError else SakaTheme.colors.primary

        bottomText?.let {
            Text(
                text = it,
                color = bottomColor,
                style = SakaTheme.typography.caption2,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

/**
 * Komponen Input Password dengan toggle visibilitas otomatis.
 *
 * @param value Nilai teks password saat ini.
 * @param onValueChange Callback saat teks berubah.
 * @param modifier Modifier untuk kustomisasi layout.
 * @param label Teks label di atas input (opsional).
 * @param placeholder Teks petunjuk (default: "Password").
 * @param helperText Teks bantuan di bawah input.
 * @param isError Menandakan apakah input dalam kondisi error.
 * @param errorMessage Pesan kesalahan yang ditampilkan jika isError true.
 */
@Composable
fun SakaPasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = "Password",
    helperText: String? = null,
    isError: Boolean = false,
    errorMessage: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
) {
    var passwordVisible by remember { mutableStateOf(false) }

    SakaTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = label,
        placeholder = placeholder,
        helperText = helperText,
        isError = isError,
        errorMessage = errorMessage,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = keyboardOptions,
        trailingIcon = {
            val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            val description = if (passwordVisible) "Hide password" else "Show password"

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, contentDescription = description, tint = SakaTheme.colors.neutralGrey)
            }
        }
    )
}

/**
 * Komponen Input Pencarian (Search Field) dengan ikon cari dan tombol hapus otomatis.
 *
 * @param value Kata kunci pencarian saat ini.
 * @param onValueChange Callback saat teks berubah.
 * @param modifier Modifier kustom.
 * @param placeholder Teks petunjuk (default: "Search...").
 * @param onClear Callback saat tombol hapus (X) diklik.
 */
@Composable
fun SakaSearchField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Search...",
    onClear: () -> Unit = {}
) {
    SakaTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        placeholder = placeholder,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = SakaTheme.colors.neutralGrey
            )
        },
        trailingIcon = if (value.isNotEmpty()) {
            {
                IconButton(onClick = onClear) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Clear", tint = SakaTheme.colors.neutralGrey)
                }
            }
        } else null
    )
}

/**
 * Komponen Input Mata Uang atau Input dengan Suffix khusus dan garis pemisah.
 * Cocok digunakan untuk form keuangan atau input dengan satuan.
 *
 * @param value Nilai teks input.
 * @param onValueChange Callback saat teks berubah.
 * @param modifier Modifier kustom.
 * @param label Teks label di atas input.
 * @param currency Teks unit/mata uang yang ditampilkan di sisi kanan (default: "USD").
 * @param placeholder Teks petunjuk.
 */
@Composable
fun SakaCurrencyField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    currency: String = "USD",
    placeholder: String? = null
) {
    SakaTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = label,
        placeholder = placeholder,
        suffix = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                HorizontalDivider(
                    modifier = Modifier
                        .height(24.dp)
                        .width(1.dp),
                    color = SakaTheme.colors.neutralSilver
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = currency, style = SakaTheme.typography.body2, color = SakaTheme.colors.neutralGrey)
                Icon(
                    imageVector = Icons.Default.UnfoldMore,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = SakaTheme.colors.neutralGrey
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun SakaTextFieldPreview() {
    MyApplicationTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            SakaTextField(
                value = "",
                onValueChange = {},
                placeholder = "Text input"
            )
            Spacer(modifier = Modifier.height(16.dp))
            SakaTextField(
                value = "Text input",
                onValueChange = {},
                helperText = "Caption"
            )
            Spacer(modifier = Modifier.height(16.dp))
            SakaPasswordField(
                value = "Password",
                onValueChange = {},
                helperText = "Caption"
            )
            Spacer(modifier = Modifier.height(16.dp))
            SakaSearchField(
                value = "Bank",
                onValueChange = {}
            )
            Spacer(modifier = Modifier.height(16.dp))
            SakaTextField(
                value = "",
                onValueChange = {},
                label = "Label",
                placeholder = "Text input",
                helperText = "Caption"
            )
            Spacer(modifier = Modifier.height(16.dp))
            SakaTextField(
                value = "Text input",
                onValueChange = {},
                label = "Label",
                helperText = "Caption",
                trailingIcon = {
                    Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            SakaCurrencyField(
                value = "Text input",
                onValueChange = {},
                label = "From"
            )
        }
    }
}
