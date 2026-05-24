/**
 * Created by Muh. Arifandi on 24/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:forgotpassword:impl
 * File : ForgotpasswordViewModel.kt
 */
package com.muh.arifandi.dicoding.features.forgotpassword.ui

import androidx.lifecycle.viewModelScope
import com.muh.arifandi.dicoding.core.architecture.mvi.BaseViewModel
import com.muh.arifandi.dicoding.features.forgotpassword.ui.state.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel untuk mengelola logika bisnis layar Forgot Password.
 */
@HiltViewModel
class ForgotpasswordViewModel @Inject constructor() :
    BaseViewModel<ForgotpasswordState, ForgotpasswordIntent, ForgotpasswordEffect>(ForgotpasswordState()) {

    override fun processIntent(intent: ForgotpasswordIntent) {
        when (intent) {
            is ForgotpasswordIntent.PhoneNumberChanged -> {
                setState { copy(phoneNumber = intent.phoneNumber) }
            }
            is ForgotpasswordIntent.Submit -> {
                sendOtp()
            }
            is ForgotpasswordIntent.OtpCodeChanged -> {
                setState { copy(otpCode = intent.code) }
            }
            is ForgotpasswordIntent.ResendCode -> {
                sendOtp()
            }
            is ForgotpasswordIntent.ChangePassword -> {
                // Sesuai permintaan: langsung diarahkan ke ganti password
                sendEffect { ForgotpasswordEffect.NavigateToVerify }
            }
            is ForgotpasswordIntent.ChangePhoneNumber -> {
                setState { copy(isCodeSent = false, otpCode = "") }
            }
            is ForgotpasswordIntent.NavigateBack -> {
                sendEffect { ForgotpasswordEffect.NavigateBack }
            }
        }
    }

    /**
     * Simulasi pengiriman OTP melalui nomor telepon.
     */
    private fun sendOtp() {
        viewModelScope.launch {
            setState { copy(isLoading = true) }
            
            // Simulasi API call
            delay(1500)
            
            setState { copy(isLoading = false, isCodeSent = true) }
            startResendTimer()
        }
    }

    private fun startResendTimer() {
        viewModelScope.launch {
            var timeLeft = 30 // Timer 30 detik
            while (timeLeft > 0) {
                setState { copy(resendTimer = timeLeft) }
                delay(1000)
                timeLeft--
            }
            setState { copy(resendTimer = 0) }
        }
    }
}
