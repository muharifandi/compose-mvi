/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:onboarding:impl
 * File : OnboardingScreen.kt
 *
 * Description:
 * Layar onboarding yang memperkenalkan fitur-fitur utama aplikasi kepada pengguna baru.
 */

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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaAsyncImage
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaButton
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaScaffold
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme
import com.muh.arifandi.dicoding.core.ui.R as CoreR
import com.muh.arifandi.dicoding.features.onboarding.R
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

    // 1. Tangani Side Effects (seperti Navigasi)
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
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { state.items.size }
    )

    // Sinkronisasi pagerState dengan state.currentPage jika ada navigasi dari tombol
    LaunchedEffect(state.currentPage) {
        if (state.items.isNotEmpty() && pagerState.currentPage != state.currentPage) {
            pagerState.animateScrollToPage(state.currentPage)
        }
    }

    // Sinkronisasi balik jika user swipe manual
    LaunchedEffect(pagerState.currentPage) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            if (state.items.isNotEmpty() && page != state.currentPage) {
                // Opsional: panggil intent untuk update currentPage di ViewModel
                // onIntent(OnboardingIntent.PageChanged(page)) 
            }
        }
    }

    SakaScaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            // Bagian bawah untuk Tombol Navigasi
            if (state.items.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth()
                ) {
                    val buttonText = if (state.currentPage == state.items.size - 1) {
                        stringResource(R.string.onboarding_start)
                    } else {
                        stringResource(R.string.onboarding_next)
                    }
                    SakaButton(
                        text = buttonText,
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
        }
    ) { paddingValues ->
        if (state.items.isNotEmpty()) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                beyondViewportPageCount = 1 // Melakukan pre-load gambar di halaman sebelahnya agar smooth
            ) { pageIndex ->
                val pageData = state.items.getOrNull(pageIndex)
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    if (pageData != null) {
                        // Ilustrasi Gambar
                        SakaAsyncImage(
                            model = pageData.imageRes,
                            modifier = Modifier.size(280.dp),
                            crossfade = false, // Matikan crossfade untuk gambar offline agar instan
                            showPlaceholder = false // Matikan placeholder agar tidak berkedip ikon
                        )

                        Spacer(modifier = Modifier.height(32.dp))

                        // Judul
                        Text(
                            text = stringResource(pageData.titleRes),
                            style = SakaTheme.typography.title1,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Deskripsi
                        Text(
                            text = stringResource(pageData.descriptionRes),
                            style = SakaTheme.typography.body3,
                            textAlign = TextAlign.Center,
                            color = SakaTheme.colors.neutralGrey
                        )
                    }
                }
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
                    OnboardingPage(
                        titleRes = R.string.onboarding_page1_title,
                        descriptionRes = R.string.onboarding_page1_desc,
                        imageRes = CoreR.drawable.ic_onboarding_1
                    )
                ),
                currentPage = 0
            ),
            onIntent = {}
        )
    }
}
