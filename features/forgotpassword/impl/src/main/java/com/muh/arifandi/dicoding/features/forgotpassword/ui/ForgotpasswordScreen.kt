/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:forgotpassword:impl
 * File : ForgotpasswordScreen.kt
 *
 * Description:
 * Layar utama Forgot Password yang menangani input nomor telepon dan verifikasi OTP.
 */
package com.muh.arifandi.dicoding.features.forgotpassword.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muh.arifandi.dicoding.core.ui.R as CoreR
import com.muh.arifandi.dicoding.core.ui.designsystem.components.*
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme
import com.muh.arifandi.dicoding.features.forgotpassword.R
import com.muh.arifandi.dicoding.features.forgotpassword.ui.state.ForgotpasswordEffect
import com.muh.arifandi.dicoding.features.forgotpassword.ui.state.ForgotpasswordIntent
import com.muh.arifandi.dicoding.features.forgotpassword.ui.state.ForgotpasswordState

/**
 * Screen utama untuk fitur Forgot Password.
 * Menangani koleksi state dan efek navigasi.
 */
/**
 * CONFIG: UI STRINGS
 * Menyimpan semua teks yang dibutuhkan oleh UI Forgot Password.
 */
data class ForgotpasswordUiStrings(
    val title: String,
    val typePhone: String,
    val phonePlaceholder: String,
    val weTextedInitial: String,
    val send: String,
    val typeCode: String,
    val codePlaceholder: String,
    val resend: String,
    val weTextedVerification: String,
    val expiryNotice: String,
    val changePassword: String,
    val changePhoneNumber: String
)

@Composable
fun ForgotpasswordScreen(
    viewModel: ForgotpasswordViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit = {},
    onNavigateToChangePassword: () -> Unit = {}
) {
    // 1. Resolve semua string di satu tempat
    val uiStrings = ForgotpasswordUiStrings(
        title = stringResource(R.string.forgotpassword_title),
        typePhone = stringResource(R.string.forgotpassword_type_phone),
        phonePlaceholder = stringResource(CoreR.string.placeholder_phone_format),
        weTextedInitial = stringResource(R.string.forgotpassword_we_texted_initial),
        send = stringResource(R.string.forgotpassword_send),
        typeCode = stringResource(R.string.forgotpassword_type_code),
        codePlaceholder = stringResource(R.string.forgotpassword_code_placeholder),
        resend = stringResource(R.string.forgotpassword_resend),
        weTextedVerification = stringResource(R.string.forgotpassword_we_texted_verification, ""),
        expiryNotice = stringResource(R.string.forgotpassword_expiry_notice),
        changePassword = stringResource(R.string.forgotpassword_change_password),
        changePhoneNumber = stringResource(R.string.forgotpassword_change_phone_number)
    )

    val state by viewModel.state.collectAsStateWithLifecycle()

    // Menangani Side Effects
    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is ForgotpasswordEffect.NavigateBack -> onNavigateBack()
                is ForgotpasswordEffect.NavigateToVerify -> onNavigateToChangePassword()
                is ForgotpasswordEffect.ShowError -> { /* Handle error */ }
            }
        }
    }

    ForgotpasswordContent(
        state = state,
        uiStrings = uiStrings,
        onIntent = { viewModel.processIntent(it) }
    )
}

@Composable
internal fun ForgotpasswordContent(
    state: ForgotpasswordState,
    uiStrings: ForgotpasswordUiStrings,
    onIntent: (ForgotpasswordIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    val dimens = SakaTheme.dimens

    SakaScaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            SakaNavigationBar(
                title = uiStrings.title,
                onBackClick = { onIntent(ForgotpasswordIntent.NavigateBack) },
                backgroundColor = Color.White,
                contentColor = SakaTheme.colors.neutralDark
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = dimens.spaceL),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(dimens.spaceL))

            // Card Container sesuai desain
            SakaCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimens.spaceL)
                ) {
                    if (!state.isCodeSent) {
                        // View awal: Input Nomor Telepon
                        Text(
                            text = uiStrings.typePhone,
                            style = SakaTheme.typography.body3,
                            color = SakaTheme.colors.neutralGrey
                        )

                        Spacer(modifier = Modifier.height(dimens.spaceS))

                        SakaTextField(
                            value = state.phoneNumber,
                            onValueChange = { onIntent(ForgotpasswordIntent.PhoneNumberChanged(it)) },
                            placeholder = uiStrings.phonePlaceholder,
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Phone,
                                imeAction = ImeAction.Done
                            )
                        )

                        Spacer(modifier = Modifier.height(dimens.spaceL))

                        Text(
                            text = uiStrings.weTextedInitial,
                            style = SakaTheme.typography.body3,
                            color = SakaTheme.colors.neutralDark
                        )

                        Spacer(modifier = Modifier.height(dimens.spaceXL))

                        SakaButton(
                            text = uiStrings.send,
                            onClick = { onIntent(ForgotpasswordIntent.Submit) },
                            modifier = Modifier.fillMaxWidth(),
                            enabled = state.isButtonEnabled,
                            isLoading = state.isLoading
                        )
                    } else {
                        // View OTP: Sesuai gambar
                        Text(
                            text = uiStrings.typeCode,
                            style = SakaTheme.typography.body3,
                            color = SakaTheme.colors.neutralGrey
                        )

                        Spacer(modifier = Modifier.height(dimens.spaceS))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Input Code (Mendominasi sisa ruang)
                            SakaTextField(
                                value = state.otpCode,
                                onValueChange = { onIntent(ForgotpasswordIntent.OtpCodeChanged(it)) },
                                placeholder = uiStrings.codePlaceholder,
                                modifier = Modifier.weight(1f),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number,
                                    imeAction = ImeAction.Done
                                )
                            )

                            Spacer(modifier = Modifier.width(dimens.spaceM))

                            // Tombol Resend (Custom agar tidak fillMaxWidth)
                            Button(
                                onClick = { onIntent(ForgotpasswordIntent.ResendCode) },
                                modifier = Modifier.height(56.dp),
                                shape = RoundedCornerShape(12.dp),
                                enabled = state.isResendEnabled,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = SakaTheme.colors.primary,
                                    contentColor = Color.White,
                                    disabledContainerColor = SakaTheme.colors.neutralGrey,
                                    disabledContentColor = Color.White
                                )
                            ) {
                                val resendText = if (state.resendTimer > 0) {
                                    "${uiStrings.resend} (${state.resendTimer}s)"
                                } else {
                                    uiStrings.resend
                                }
                                Text(
                                    text = resendText,
                                    style = SakaTheme.typography.title3
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(dimens.spaceL))

                        val annotatedInfo = buildAnnotatedString {
                            append(uiStrings.weTextedVerification)
                            withStyle(style = SpanStyle(color = SakaTheme.colors.primary, fontWeight = FontWeight.Bold)) {
                                append(state.phoneNumber)
                            }
                        }

                        Text(
                            text = annotatedInfo,
                            style = SakaTheme.typography.body3,
                            color = SakaTheme.colors.neutralGrey
                        )

                        Spacer(modifier = Modifier.height(dimens.spaceM))

                        Text(
                            text = uiStrings.expiryNotice,
                            style = SakaTheme.typography.body3,
                            color = SakaTheme.colors.neutralGrey
                        )

                        Spacer(modifier = Modifier.height(dimens.spaceXL))

                        SakaButton(
                            text = uiStrings.changePassword,
                            onClick = { onIntent(ForgotpasswordIntent.ChangePassword) },
                            modifier = Modifier.fillMaxWidth(),
                            enabled = state.isOtpButtonEnabled,
                            isLoading = state.isLoading
                        )
                    }
                }
            }

            if (state.isCodeSent) {
                Spacer(modifier = Modifier.height(dimens.spaceXXL))

                TextButton(onClick = { onIntent(ForgotpasswordIntent.ChangePhoneNumber) }) {
                    Text(
                        text = uiStrings.changePhoneNumber,
                        style = SakaTheme.typography.body3,
                        color = SakaTheme.colors.primary,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun ForgotpasswordScreenPreview() {
    MyApplicationTheme {
        ForgotpasswordContent(
            state = ForgotpasswordState(),
            uiStrings = ForgotpasswordUiStrings(
                title = "Title",
                typePhone = "Type Phone",
                phonePlaceholder = "Placeholder",
                weTextedInitial = "Initial Text",
                send = "Send",
                typeCode = "Type Code",
                codePlaceholder = "Code",
                resend = "Resend",
                weTextedVerification = "Verification Text",
                expiryNotice = "Expiry",
                changePassword = "Change",
                changePhoneNumber = "Change Phone"
            ),
            onIntent = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ForgotpasswordOtpPreview() {
    MyApplicationTheme {
        ForgotpasswordContent(
            state = ForgotpasswordState(
                phoneNumber = "(+84) 0398829xxx",
                otpCode = "",
                isCodeSent = true,
                isLoading = false
            ),
            uiStrings = ForgotpasswordUiStrings(
                title = "Title",
                typePhone = "Type Phone",
                phonePlaceholder = "Placeholder",
                weTextedInitial = "Initial Text",
                send = "Send",
                typeCode = "Type Code",
                codePlaceholder = "Code",
                resend = "Resend",
                weTextedVerification = "Verification Text",
                expiryNotice = "Expiry",
                changePassword = "Change",
                changePhoneNumber = "Change Phone"
            ),
            onIntent = {}
        )
    }
}
