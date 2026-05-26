/**
 * Created by Muh. Arifandi on 25/05/26.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: ForgotpasswordViewModel
 */
package com.muh.arifandi.dicoding.features.forgotpassword.ui

import androidx.lifecycle.viewModelScope
import com.muh.arifandi.dicoding.core.architecture.mvi.BaseViewModel
import com.muh.arifandi.dicoding.features.forgotpassword.domain.usecase.ForgotpasswordUseCase
import com.muh.arifandi.dicoding.features.forgotpassword.ui.state.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotpasswordViewModel @Inject constructor(
    private val useCase: ForgotpasswordUseCase
) : BaseViewModel<ForgotpasswordState, ForgotpasswordIntent, ForgotpasswordEffect>(ForgotpasswordState()) {

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

    private fun sendOtp() {
        viewModelScope.launch {
            setState { copy(isLoading = true) }
            val result = useCase.sendOtp(state.value.phoneNumber)
            setState { copy(isLoading = false) }
            
            result.onSuccess {
                setState { copy(isCodeSent = true) }
                startResendTimer()
            }
        }
    }

    private fun startResendTimer() {
        viewModelScope.launch {
            var timeLeft = 30
            while (timeLeft > 0) {
                setState { copy(resendTimer = timeLeft) }
                delay(1000)
                timeLeft--
            }
            setState { copy(resendTimer = 0) }
        }
    }
}
