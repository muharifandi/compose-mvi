/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:forgotpassword:impl
 * File : ChangePasswordScreen.kt
 *
 * Description:
 * Layar untuk mengganti kata sandi baru dengan validasi konfirmasi password.
 */
package com.muh.arifandi.dicoding.features.forgotpassword.ui.changepassword

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muh.arifandi.dicoding.core.ui.designsystem.components.*
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme
import com.muh.arifandi.dicoding.features.forgotpassword.R
import com.muh.arifandi.dicoding.features.forgotpassword.ui.changepassword.state.*

data class ChangePasswordUiStrings(
    val title: String,
    val typeNew: String,
    val confirm: String,
    val placeholder: String,
    val button: String,
    val errorMismatch: String,
)

@Composable
fun ChangePasswordScreen(
    viewModel: ChangePasswordViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
    onSuccess: () -> Unit
) {
    val uiStrings = ChangePasswordUiStrings(
        title = stringResource(R.string.changepassword_title),
        typeNew = stringResource(R.string.changepassword_type_new),
        confirm = stringResource(R.string.changepassword_confirm),
        placeholder = stringResource(R.string.changepassword_placeholder),
        button = stringResource(R.string.changepassword_button),
        errorMismatch = stringResource(R.string.changepassword_error_mismatch)
    )

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is ChangePasswordEffect.NavigateBack -> onNavigateBack()
                is ChangePasswordEffect.Success -> onSuccess()
                is ChangePasswordEffect.ShowError -> { /* Handle Error */ }
            }
        }
    }

    ChangePasswordContent(
        state = state,
        uiStrings = uiStrings,
        onIntent = { viewModel.processIntent(it) }
    )
}

@Composable
internal fun ChangePasswordContent(
    state: ChangePasswordState,
    uiStrings: ChangePasswordUiStrings,
    onIntent: (ChangePasswordIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    val dimens = SakaTheme.dimens

    SakaScaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            SakaNavigationBar(
                title = uiStrings.title,
                onBackClick = { onIntent(ChangePasswordIntent.NavigateBack) },
                backgroundColor = Color.White,
                contentColor = SakaTheme.colors.neutralDark
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = dimens.spaceL)
        ) {
            Spacer(modifier = Modifier.height(dimens.spaceL))

            SakaCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimens.spaceL)
                ) {
                    Text(
                        text = uiStrings.typeNew,
                        style = SakaTheme.typography.body3,
                        color = SakaTheme.colors.neutralGrey
                    )

                    Spacer(modifier = Modifier.height(dimens.spaceS))

                    SakaPasswordField(
                        value = state.newPassword,
                        onValueChange = { onIntent(ChangePasswordIntent.NewPasswordChanged(it)) },
                        placeholder = uiStrings.placeholder,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(dimens.spaceL))

                    Text(
                        text = uiStrings.confirm,
                        style = SakaTheme.typography.body3,
                        color = SakaTheme.colors.neutralGrey
                    )

                    Spacer(modifier = Modifier.height(dimens.spaceS))

                    SakaPasswordField(
                        value = state.confirmPassword,
                        onValueChange = { onIntent(ChangePasswordIntent.ConfirmPasswordChanged(it)) },
                        placeholder = uiStrings.placeholder,
                        modifier = Modifier.fillMaxWidth(),
                        isError = state.isConfirmPasswordError,
                        errorMessage = if (state.isConfirmPasswordError) uiStrings.errorMismatch else null
                    )

                    Spacer(modifier = Modifier.height(dimens.spaceXL))

                    SakaButton(
                        text = uiStrings.button,
                        onClick = { onIntent(ChangePasswordIntent.Submit) },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = state.isButtonEnabled,
                        isLoading = state.isLoading
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChangePasswordPreview() {
    MyApplicationTheme {
        ChangePasswordContent(
            state = ChangePasswordState(),
            uiStrings = ChangePasswordUiStrings(
                title = "Change password",
                typeNew = "Type your new password",
                confirm = "Confirm password",
                placeholder = "**********",
                button = "Change password",
                errorMismatch = "Password konfirmasi tidak cocok"
            ),
            onIntent = {}
        )
    }
}
