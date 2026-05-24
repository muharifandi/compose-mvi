/**
 * Created by Muh. Arifandi on 24/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:forgotpassword:impl
 * File : ForgotpasswordIntent.kt
 */
package com.muh.arifandi.dicoding.features.forgotpassword.ui.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiIntent

/**
 * Daftar interaksi pengguna pada layar Forgot Password.
 */
sealed interface ForgotpasswordIntent : UiIntent {
    /**
     * Dipanggil saat input nomor telepon berubah.
     */
    data class PhoneNumberChanged(val phoneNumber: String) : ForgotpasswordIntent
    
    /**
     * Dipanggil saat pengguna menekan tombol "Send".
     */
    data object Submit : ForgotpasswordIntent

    /**
     * Dipanggil saat input kode OTP berubah.
     */
    data class OtpCodeChanged(val code: String) : ForgotpasswordIntent

    /**
     * Dipanggil saat pengguna menekan tombol "Resend".
     */
    data object ResendCode : ForgotpasswordIntent

    /**
     * Dipanggil saat pengguna menekan tombol "Change password".
     */
    data object ChangePassword : ForgotpasswordIntent

    /**
     * Dipanggil saat pengguna ingin mengganti nomor telepon.
     */
    data object ChangePhoneNumber : ForgotpasswordIntent
    
    /**
     * Dipanggil saat pengguna menekan tombol kembali.
     */
    data object NavigateBack : ForgotpasswordIntent
}
