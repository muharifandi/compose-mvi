package com.muh.arifandi.dicoding.features.register.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muh.arifandi.dicoding.core.ui.R as CoreR
import com.muh.arifandi.dicoding.core.ui.designsystem.components.*
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme
import com.muh.arifandi.dicoding.features.register.R
import com.muh.arifandi.dicoding.features.register.ui.state.RegisterEffect
import com.muh.arifandi.dicoding.features.register.ui.state.RegisterIntent
import com.muh.arifandi.dicoding.features.register.ui.state.RegisterState

/**
 * SCREEN: REGISTER
 * Menggunakan sistem responsivitas otomatis dan desain MVI.
 */
/**
 * CONFIG: UI STRINGS
 * Menyimpan semua teks yang dibutuhkan oleh UI Register.
 */
data class RegisterUiStrings(
    val title: String,
    val welcome: String,
    val subtitle: String,
    val namePlaceholder: String,
    val emailPlaceholder: String,
    val passwordPlaceholder: String,
    val termsPrefix: String,
    val termsHighlight: String,
    val signUp: String,
    val haveAccount: String,
    val signIn: String
)

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    // 1. Resolve semua string di satu tempat
    val uiStrings = RegisterUiStrings(
        title = stringResource(R.string.register_title),
        welcome = stringResource(R.string.register_welcome),
        subtitle = stringResource(R.string.register_subtitle),
        namePlaceholder = stringResource(CoreR.string.placeholder_name),
        emailPlaceholder = stringResource(CoreR.string.placeholder_email),
        passwordPlaceholder = stringResource(CoreR.string.placeholder_password),
        termsPrefix = "By creating an account your agree to our ", // Tetap hardcoded karena kompleksitas buildAnnotatedString, atau bisa di-resource-kan jika perlu
        termsHighlight = "Term and Conditions",
        signUp = stringResource(R.string.register_sign_up),
        haveAccount = stringResource(R.string.register_have_account),
        signIn = stringResource(R.string.register_sign_in)
    )

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is RegisterEffect.NavigateToLogin -> onNavigateBack()
                is RegisterEffect.ShowError -> { /* Handle Error */ }
            }
        }
    }

    RegisterContent(
        state = state,
        uiStrings = uiStrings,
        onIntent = { viewModel.processIntent(it) },
        onBackClick = onNavigateBack
    )
}

@Composable
internal fun RegisterContent(
    state: RegisterState,
    uiStrings: RegisterUiStrings,
    onIntent: (RegisterIntent) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dimens = SakaTheme.dimens

    SakaScaffold(
        modifier = modifier,
        containerColor = SakaTheme.colors.primary,
        topBar = {
            SakaNavigationBar(
                title = uiStrings.title,
                onBackClick = onBackClick,
                backgroundColor = Color.Transparent,
                contentColor = Color.White
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            
            Spacer(modifier = Modifier.height(dimens.screenHeight * 0.02f))

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = SakaTheme.colors.neutralWhite,
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
            ) {
                SakaResponsiveColumn(
                    content = {
                        Spacer(modifier = Modifier.height(dimens.spaceXL))

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

                        // Illustration - Adaptive Scale
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

                        SakaTextField(
                            value = state.name,
                            onValueChange = { onIntent(RegisterIntent.NameChanged(it)) },
                            placeholder = uiStrings.namePlaceholder,
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            )
                        )

                        Spacer(modifier = Modifier.height(dimens.spaceM))

                        SakaTextField(
                            value = state.email,
                            onValueChange = { onIntent(RegisterIntent.EmailChanged(it)) },
                            placeholder = uiStrings.emailPlaceholder,
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email,
                                imeAction = ImeAction.Next
                            )
                        )

                        Spacer(modifier = Modifier.height(dimens.spaceM))

                        SakaPasswordField(
                            value = state.password,
                            onValueChange = { onIntent(RegisterIntent.PasswordChanged(it)) },
                            placeholder = uiStrings.passwordPlaceholder,
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done
                            )
                        )

                        Spacer(modifier = Modifier.height(dimens.spaceXL))

                        SakaCheckbox(
                            checked = state.isAgreed,
                            onCheckedChange = { onIntent(RegisterIntent.AgreementChanged(it)) },
                            label = {
                                Text(
                                    text = buildAnnotatedString {
                                        append(uiStrings.termsPrefix)
                                        withStyle(style = SpanStyle(
                                            color = SakaTheme.colors.primary,
                                            fontWeight = FontWeight.Bold
                                        )) {
                                            append(uiStrings.termsHighlight)
                                        }
                                    },
                                    style = SakaTheme.typography.body3,
                                    color = SakaTheme.colors.neutralDark
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(dimens.spaceXXL))

                        SakaButton(
                            text = uiStrings.signUp,
                            onClick = { onIntent(RegisterIntent.Submit) },
                            type = SakaButtonType.PRIMARY,
                            modifier = Modifier.fillMaxWidth(),
                            isLoading = state.isLoading,
                            enabled = state.name.isNotEmpty() && 
                                      state.email.isNotEmpty() && 
                                      state.password.isNotEmpty() && 
                                      state.isAgreed
                        )
                    },
                    footer = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = uiStrings.haveAccount,
                                style = SakaTheme.typography.body3,
                                color = SakaTheme.colors.neutralDark
                            )
                            SakaButton(
                                text = uiStrings.signIn,
                                onClick = { onIntent(RegisterIntent.NavigateToLogin) },
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
private fun RegisterScreenPreview() {
    MyApplicationTheme {
        RegisterContent(
            state = RegisterState(),
            uiStrings = RegisterUiStrings(
                title = "Sign Up",
                welcome = "Welcome",
                subtitle = "Subtitle",
                namePlaceholder = "Name",
                emailPlaceholder = "Email",
                passwordPlaceholder = "Password",
                termsPrefix = "Prefix ",
                termsHighlight = "Terms",
                signUp = "Sign Up",
                haveAccount = "Have Account?",
                signIn = "Sign In"
            ),
            onIntent = {},
            onBackClick = {}
        )
    }
}
