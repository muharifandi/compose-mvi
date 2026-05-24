/**
 * Created by Muh. Arifandi on 24/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:forgotpassword:impl
 * File : ForgotpasswordState.kt
 */
package com.muh.arifandi.dicoding.features.forgotpassword.ui.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiState
import androidx.compose.runtime.Immutable

/**
 * State untuk layar Forgot Password.
 * @property phoneNumber Nomor telepon yang dimasukkan pengguna.
 * @property otpCode Kode OTP yang dimasukkan pengguna.
 * @property isCodeSent Menandakan apakah kode OTP sudah dikirim ke pengguna.
 * @property isLoading Menandakan proses pengiriman data sedang berlangsung.
 * @property error Pesan error jika terjadi kesalahan.
 */
@Immutable
data class ForgotpasswordState(
    val phoneNumber: String = "",
    val otpCode: String = "",
    val isCodeSent: Boolean = false,
    val isLoading: Boolean = false,
    val resendTimer: Int = 0,
    val error: String? = null
) : UiState {
    // Tombol aktif jika nomor telepon tidak kosong (bisa ditambahkan validasi regex jika perlu)
    val isButtonEnabled: Boolean get() = phoneNumber.isNotBlank() && !isLoading
    
    // Tombol ganti password aktif jika kode OTP diisi (misal 4-6 digit)
    val isOtpButtonEnabled: Boolean get() = otpCode.length >= 4 && !isLoading

    // Tombol resend aktif jika timer sudah habis
    val isResendEnabled: Boolean get() = resendTimer <= 0 && !isLoading
}
