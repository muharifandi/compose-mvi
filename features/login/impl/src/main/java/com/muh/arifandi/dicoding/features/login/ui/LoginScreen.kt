package com.muh.arifandi.dicoding.features.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaButton
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaButtonType
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaNavigationBar
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaPasswordField
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaResponsiveColumn
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaScaffold
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaTextField
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme
import com.muh.arifandi.dicoding.core.ui.R as CoreR
import com.muh.arifandi.dicoding.features.login.R
import com.muh.arifandi.dicoding.features.login.ui.state.LoginEffect
import com.muh.arifandi.dicoding.features.login.ui.state.LoginIntent
import com.muh.arifandi.dicoding.features.login.ui.state.LoginState

/**
 * SCREEN: LOGIN
 * Deskripsi: Layar utama untuk autentikasi pengguna.
 * 
 * TEKNOLOGI & KONSEP YANG DIGUNAKAN:
 * 1. MVI (Model-View-Intent): Menggunakan StateFlow untuk State dan Channel untuk Side Effects.
 * 2. Jetpack Compose: UI Kit modern berbasis deklaratif.
 * 3. Hilt Injection: Untuk manajemen Dependency Injection pada ViewModel.
 * 4. Responsiveness: Menggunakan sistem SakaDimens untuk adaptasi layar otomatis.
 */

/**
 * CONFIG: UI STRINGS
 * Menyimpan semua teks yang dibutuhkan oleh UI.
 * Dipusatkan di sini agar UI Composable tetap bersih dari logika stringResource.
 */
data class LoginUiStrings(
    val title: String,
    val welcome: String,
    val subtitle: String,
    val emailPlaceholder: String,
    val passwordPlaceholder: String,
    val forgotPassword: String,
    val signIn: String,
    val noAccount: String,
    val signUp: String,
)

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigateToHome: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToForgotPassword: () -> Unit
) {
    // 1. Resolve semua string di satu tempat
    val uiStrings = LoginUiStrings(
        title = stringResource(R.string.login_sign_in),
        welcome = stringResource(R.string.login_welcome),
        subtitle = stringResource(R.string.login_subtitle),
        emailPlaceholder = stringResource(CoreR.string.placeholder_email),
        passwordPlaceholder = stringResource(CoreR.string.placeholder_password),
        forgotPassword = stringResource(R.string.login_forgot_password),
        signIn = stringResource(R.string.login_sign_in),
        noAccount = stringResource(R.string.login_no_account),
        signUp = stringResource(R.string.login_sign_up)
    )

    // Collect State dengan lifecycle-aware
    val state by viewModel.state.collectAsStateWithLifecycle()

    // Side Effect Handling
    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is LoginEffect.NavigateToHome -> onNavigateToHome()
                is LoginEffect.NavigateToRegister -> onNavigateToRegister()
                is LoginEffect.NavigateToForgotPassword -> onNavigateToForgotPassword()
            }
        }
    }

    LoginContent(
        state = state,
        uiStrings = uiStrings,
        onIntent = { viewModel.processIntent(it) }
    )
}

@Composable
internal fun LoginContent(
    state: LoginState,
    uiStrings: LoginUiStrings,
    onIntent: (LoginIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    /**
     * SAKA THEME DIMENS:
     * Mengambil konfigurasi dimensi otomatis (isTablet, screenHeight, dll).
     */
    val dimens = SakaTheme.dimens

    SakaScaffold(
        modifier = modifier,
        containerColor = SakaTheme.colors.primary,
        topBar = {
            SakaNavigationBar(
                title = uiStrings.title,
                onBackClick = { /* Implementasi Back */ },
                backgroundColor = Color.Transparent,
                contentColor = Color.White
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            
            // Header spacing menggunakan persentase tinggi layar
            Spacer(modifier = Modifier.height(dimens.screenHeight * 0.02f))

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = SakaTheme.colors.neutralWhite,
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
            ) {
                SakaResponsiveColumn(
                    content = {
                        Spacer(modifier = Modifier.height(dimens.spaceXL))

                        // Teks menggunakan uiStrings dari config
                        Text(
                            text = uiStrings.welcome,
                            style = SakaTheme.typography.title1,
                            color = SakaTheme.colors.primary,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = uiStrings.subtitle,
                            style = SakaTheme.typography.body3,
                            color = SakaTheme.colors.neutralGrey,
                            modifier = Modifier.fillMaxWidth()
                        )
                        
                        Spacer(modifier = Modifier.height(dimens.spaceXL))

                        /**
                         * ADAPTIVE ILLUSTRATION
                         */
                        val illustrationScale = if (dimens.screenHeight < 650.dp) 0.35f else 0.5f
                        Image(
                            painter = painterResource(id = CoreR.drawable.ic_illustration_login),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth(illustrationScale)
                                .aspectRatio(1f)
                                .padding(vertical = dimens.spaceL),
                            contentScale = ContentScale.Fit
                        )

                        Spacer(modifier = Modifier.height(dimens.spaceXL))

                        // INPUT FIELDS: Menggunakan placeholder dari uiStrings
                        SakaTextField(
                            value = state.email,
                            onValueChange = { onIntent(LoginIntent.EmailChanged(it)) },
                            placeholder = uiStrings.emailPlaceholder,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(dimens.spaceM))

                        SakaPasswordField(
                            value = state.password,
                            onValueChange = { onIntent(LoginIntent.PasswordChanged(it)) },
                            placeholder = uiStrings.passwordPlaceholder,
                            modifier = Modifier.fillMaxWidth()
                        )

                        SakaButton(
                            text = uiStrings.forgotPassword,
                            onClick = { onIntent(LoginIntent.NavigateToForgotPassword) },
                            type = SakaButtonType.LINK,
                            modifier = Modifier.align(Alignment.End)
                        )

                        Spacer(modifier = Modifier.height(dimens.spaceXL))

                        SakaButton(
                            text = uiStrings.signIn,
                            onClick = { onIntent(LoginIntent.Submit) },
                            type = SakaButtonType.PRIMARY,
                            modifier = Modifier.fillMaxWidth(),
                            isLoading = state.isLoading,
                            enabled = state.email.isNotEmpty() && state.password.isNotEmpty()
                        )

                        Spacer(modifier = Modifier.height(dimens.spaceXL))

                        // BIOMETRIC OPTION
                        Icon(
                            imageVector = Icons.Default.Fingerprint,
                            contentDescription = "Biometric",
                            tint = SakaTheme.colors.primary,
                            modifier = Modifier
                                .size(64.dp)
                                .clickable { /* Biometric Action */ }
                        )
                    },
                    footer = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = uiStrings.noAccount,
                                style = SakaTheme.typography.body3,
                                color = SakaTheme.colors.neutralDark
                            )
                            SakaButton(
                                text = uiStrings.signUp,
                                onClick = { onIntent(LoginIntent.NavigateToRegister) },
                                type = SakaButtonType.LINK
                            )
                        }
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    MyApplicationTheme {
        LoginContent(
            state = LoginState(),
            uiStrings = LoginUiStrings(
                title = "Sign In",
                welcome = "Welcome",
                subtitle = "Subtitle",
                emailPlaceholder = "Email",
                passwordPlaceholder = "Password",
                forgotPassword = "Forgot?",
                signIn = "Sign In",
                noAccount = "No Account?",
                signUp = "Sign Up"
            ),
            onIntent = {}
        )
    }
}
