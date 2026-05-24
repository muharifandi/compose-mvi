package com.muh.arifandi.dicoding.features.register.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaLoadingView
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme
import com.muh.arifandi.dicoding.features.register.ui.state.RegisterState

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    RegisterContent(state = state)
}

@Composable
internal fun RegisterContent(
    state: RegisterState,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            SakaLoadingView()
        } else {
            Text(text = "Welcome to Register Screen")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterScreenPreview() {
    MyApplicationTheme {
        RegisterContent(
            state = RegisterState(
                isLoading = false,
                data = "Preview Data"
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterScreenLoadingPreview() {
    MyApplicationTheme {
        RegisterContent(
            state = RegisterState(
                isLoading = true
            )
        )
    }
}
