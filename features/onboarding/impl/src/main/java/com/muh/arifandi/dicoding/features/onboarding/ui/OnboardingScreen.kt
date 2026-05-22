package com.muh.arifandi.dicoding.features.onboarding.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaAsyncImage
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaButton
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaLoadingView
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaScaffold
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme
import com.muh.arifandi.dicoding.features.onboarding.domain.model.OnboardingPage
import com.muh.arifandi.dicoding.features.onboarding.ui.state.OnboardingEffect
import com.muh.arifandi.dicoding.features.onboarding.ui.state.OnboardingIntent
import com.muh.arifandi.dicoding.features.onboarding.ui.state.OnboardingState

@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel = hiltViewModel(),
    onNavigateToLogin: () -> Unit // Callback navigasi ke fitur lain
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    // 1. Trigger muat data saat pertama kali layar dibuka
    LaunchedEffect(Unit) {
        viewModel.processIntent(OnboardingIntent.LoadPages)
    }

    // 2. Tangani Side Effects (seperti Navigasi)
    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is OnboardingEffect.NavigateToLogin -> onNavigateToLogin()
            }
        }
    }

    // 3. Panggil UI Murni
    OnboardingContent(
        state = state,
        onIntent = { viewModel.processIntent(it) }
    )
}

@Composable
internal fun OnboardingContent(
    state: OnboardingState,
    onIntent: (OnboardingIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    // Ambil data halaman saat ini dari list yang ada di state
    val currentPageData = state.items.getOrNull(state.currentPage)

    SakaScaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            // Bagian bawah untuk Tombol Navigasi
            Box(modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()) {
                SakaButton(
                    text = if (state.currentPage == state.items.size - 1) "Mulai" else "Lanjut",
                    onClick = {
                        if (state.currentPage == state.items.size - 1) {
                            onIntent(OnboardingIntent.GetStarted)
                        } else {
                            onIntent(OnboardingIntent.NextPage)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally, // Sumbu silang (Horizontal)
            verticalArrangement = Arrangement.Center
        ) {
            if (currentPageData != null) {
                // Ilustrasi Gambar
                SakaAsyncImage(
                    model = currentPageData.imageRes,
                    modifier = Modifier.size(280.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Judul
                Text(
                    text = currentPageData.title,
                    style = SakaTheme.typography.title1,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Deskripsi
                Text(
                    text = currentPageData.description,
                    style = SakaTheme.typography.body3,
                    textAlign = TextAlign.Center,
                    color = SakaTheme.colors.neutralGrey
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingPreview() {
    MyApplicationTheme {
        OnboardingContent(
            state = OnboardingState(
                items = listOf(
                    OnboardingPage("Judul Preview", "Deskripsi singkat untuk testing.", 0)
                ),
                currentPage = 0
            ),
            onIntent = {}
        )
    }
}
