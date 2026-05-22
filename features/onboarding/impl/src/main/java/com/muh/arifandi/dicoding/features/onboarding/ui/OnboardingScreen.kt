package com.muh.arifandi.dicoding.features.onboarding.ui

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
import com.muh.arifandi.dicoding.features.onboarding.ui.state.OnboardingState

@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    OnboardingContent(state = state)
}

@Composable
internal fun OnboardingContent(
    state: OnboardingState,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            SakaLoadingView()
        } else {
            Text(text = "Welcome to Onboarding Screen")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingScreenPreview() {
    MyApplicationTheme {
        OnboardingContent(
            state = OnboardingState(
                isLoading = false,
                data = "Preview Data"
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingScreenLoadingPreview() {
    MyApplicationTheme {
        OnboardingContent(
            state = OnboardingState(
                isLoading = true
            )
        )
    }
}
