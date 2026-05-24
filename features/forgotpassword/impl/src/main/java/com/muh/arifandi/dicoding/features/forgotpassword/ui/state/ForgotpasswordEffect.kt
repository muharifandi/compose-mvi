/**
 * Created by Muh. Arifandi on 24/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:forgotpassword:impl
 * File : ForgotpasswordEffect.kt
 */
package com.muh.arifandi.dicoding.features.forgotpassword.ui.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiEffect

/**
 * Side effects untuk layar Forgot Password.
 */
sealed interface ForgotpasswordEffect : UiEffect {
    /**
     * Menampilkan pesan kesalahan.
     */
    data class ShowError(val message: String) : ForgotpasswordEffect
    
    /**
     * Navigasi kembali ke layar sebelumnya.
     */
    data object NavigateBack : ForgotpasswordEffect
    
    /**
     * Navigasi ke layar verifikasi OTP.
     */
    data object NavigateToVerify : ForgotpasswordEffect
}
