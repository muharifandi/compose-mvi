/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:forgotpassword:impl
 * File : ForgotpasswordScreenTest.kt
 *
 * Description:
 * Pengujian UI untuk alur Forgot Password, termasuk input nomor telepon dan timer resend OTP.
 */

package com.muh.arifandi.dicoding.features.forgotpassword.ui

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme
import com.muh.arifandi.dicoding.features.forgotpassword.ui.state.ForgotpasswordState
import org.junit.Rule
import org.junit.Test

class ForgotpasswordScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    /**
     * Objek konfigurasi string untuk UI Forgot Password.
     */
    private val uiStrings = ForgotpasswordUiStrings(
        title = "Forgot password",
        typePhone = "Type your phone number",
        phonePlaceholder = "(+84)",
        weTextedInitial = "We texted you a code to verify your phone number",
        send = "Send",
        typeCode = "Type a code",
        codePlaceholder = "Code",
        resend = "Resend",
        weTextedVerification = "We texted you a code to verify your phone number ",
        expiryNotice = "This code will expired 10 minutes after this message.",
        changePassword = "Change password",
        changePhoneNumber = "Change your phone number"
    )

    /**
     * Skenario: Memastikan tampilan awal fitur Lupa Password (Input Nomor Telepon).
     * Ekspektasi: Layar menampilkan instruksi input nomor telepon dan tombol Send.
     */
    @Test
    fun forgotPassword_initialState_showsPhoneInput() {
        composeTestRule.setContent {
            MyApplicationTheme {
                ForgotpasswordContent(
                    state = ForgotpasswordState(isCodeSent = false),
                    uiStrings = uiStrings,
                    onIntent = {}
                )
            }
        }

        composeTestRule.onNodeWithText(uiStrings.typePhone).assertIsDisplayed()
        composeTestRule.onNodeWithText(uiStrings.phonePlaceholder).assertIsDisplayed()
        composeTestRule.onNodeWithText(uiStrings.send).assertIsDisplayed()
    }

    /**
     * Skenario: Memastikan transisi ke tampilan Input OTP setelah kode terkirim.
     * Ekspektasi: Layar menyembunyikan input nomor dan menampilkan input kode OTP serta tombol ganti password.
     */
    @Test
    fun forgotPassword_codeSentState_showsOtpInput() {
        composeTestRule.setContent {
            MyApplicationTheme {
                ForgotpasswordContent(
                    state = ForgotpasswordState(isCodeSent = true, phoneNumber = "081234567"),
                    uiStrings = uiStrings,
                    onIntent = {}
                )
            }
        }

        composeTestRule.onNodeWithText(uiStrings.typeCode).assertIsDisplayed()
        composeTestRule.onNodeWithText(uiStrings.resend).assertIsDisplayed()
        composeTestRule.onNodeWithText(uiStrings.changePassword).assertIsDisplayed()
        composeTestRule.onNodeWithText(uiStrings.changePhoneNumber).assertIsDisplayed()
    }

    /**
     * Skenario: Validasi tombol Send saat nomor telepon belum diisi.
     * Ekspektasi: Tombol Send tidak aktif (disabled).
     */
    @Test
    fun sendButton_isDisabled_whenPhoneIsEmpty() {
        composeTestRule.setContent {
            MyApplicationTheme {
                ForgotpasswordContent(
                    state = ForgotpasswordState(phoneNumber = "", isCodeSent = false),
                    uiStrings = uiStrings,
                    onIntent = {}
                )
            }
        }

        composeTestRule.onNodeWithText(uiStrings.send).assertIsNotEnabled()
    }

    /**
     * Skenario: Validasi tombol Ganti Password saat kode OTP belum diisi.
     * Ekspektasi: Tombol Ganti Password tidak aktif (disabled) meskipun sudah berada di tahap input OTP.
     */
    @Test
    fun changePasswordButton_isDisabled_whenOtpIsEmpty() {
        composeTestRule.setContent {
            MyApplicationTheme {
                ForgotpasswordContent(
                    state = ForgotpasswordState(otpCode = "", isCodeSent = true),
                    uiStrings = uiStrings,
                    onIntent = {}
                )
            }
        }

        composeTestRule.onNodeWithText(uiStrings.changePassword).assertIsNotEnabled()
    }

    /**
     * Skenario: Validasi tombol Resend saat timer masih berjalan.
     * Ekspektasi: Tombol Resend tidak aktif dan menampilkan sisa waktu.
     */
    @Test
    fun resendButton_isDisabled_whenTimerIsActive() {
        val timerValue = 25
        composeTestRule.setContent {
            MyApplicationTheme {
                ForgotpasswordContent(
                    state = ForgotpasswordState(isCodeSent = true, resendTimer = timerValue),
                    uiStrings = uiStrings,
                    onIntent = {}
                )
            }
        }

        val expectedText = "${uiStrings.resend} (${timerValue}s)"
        composeTestRule.onNodeWithText(expectedText).assertIsDisplayed()
        composeTestRule.onNodeWithText(expectedText).assertIsNotEnabled()
    }

    /**
     * Skenario: Validasi tombol Resend saat timer habis.
     * Ekspektasi: Tombol Resend aktif dan hanya menampilkan teks "Resend".
     */
    @Test
    fun resendButton_isEnabled_whenTimerIsZero() {
        composeTestRule.setContent {
            MyApplicationTheme {
                ForgotpasswordContent(
                    state = ForgotpasswordState(isCodeSent = true, resendTimer = 0),
                    uiStrings = uiStrings,
                    onIntent = {}
                )
            }
        }

        composeTestRule.onNodeWithText(uiStrings.resend).assertIsDisplayed()
        composeTestRule.onNodeWithText(uiStrings.resend).assertIsEnabled()
    }
}
