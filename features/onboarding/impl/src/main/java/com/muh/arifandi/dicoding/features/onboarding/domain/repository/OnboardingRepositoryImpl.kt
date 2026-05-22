package com.muh.arifandi.dicoding.features.onboarding.domain.repository

import com.muh.arifandi.dicoding.core.ui.R
import com.muh.arifandi.dicoding.features.onboarding.domain.model.OnboardingPage
import javax.inject.Inject

/**
 * Created by Muh. Arifandi on 23/05/26.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: OnboardingRepositoryImpl
 */
class OnboardingRepositoryImpl @Inject constructor() : OnboardingRepository {
    override fun getOnboardingPages(): List<OnboardingPage> {
        return listOf(
            OnboardingPage(
                title = "Kelola Saldo",
                description = "Pantau saldo dan transaksi perbankan Anda kapan saja.",
                imageRes = R.drawable.ic_onboarding_1 // Ganti dengan drawable Anda
            ),
            OnboardingPage(
                title = "Transfer Aman",
                description = "Kirim uang ke mana saja dengan enkripsi tingkat tinggi.",
                imageRes = R.drawable.ic_onboarding_2
            ),
            OnboardingPage(
                title = "Investasi Masa Depan",
                description = "Mulai investasi reksa dana langsung dari aplikasi.",
                imageRes = R.drawable.ic_onboarding_3
            )
        )
    }
}