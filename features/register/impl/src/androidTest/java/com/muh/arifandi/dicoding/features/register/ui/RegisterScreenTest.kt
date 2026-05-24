/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:register:impl
 * File : RegisterScreenTest.kt
 *
 * Description:
 * Unit test UI untuk layar Register, memvalidasi kelengkapan data dan persetujuan syarat.
 */

package com.muh.arifandi.dicoding.features.register.ui

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme
import com.muh.arifandi.dicoding.features.register.ui.state.RegisterState
import org.junit.Rule
import org.junit.Test

class RegisterScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    /**
     * Objek konfigurasi string untuk UI Register.
     */
    private val uiStrings = RegisterUiStrings(
        title = "Sign Up",
        welcome = "Welcome to us,",
        subtitle = "Hello there, create New account",
        namePlaceholder = "Full Name",
        emailPlaceholder = "Email Address",
        passwordPlaceholder = "Password",
        termsPrefix = "By creating an account your agree to our ",
        termsHighlight = "Term and Conditions",
        signUp = "Sign up",
        haveAccount = "Have an account? ",
        signIn = "Sign In"
    )

    /**
     * Skenario: Verifikasi tampilan awal layar pendaftaran.
     * Ekspektasi: Seluruh komponen input (Nama, Email, Password) dan tombol pendaftaran tampil dengan benar.
     */
    @Test
    fun registerScreen_initialState_displaysAllViews() {
        composeTestRule.setContent {
            MyApplicationTheme {
                RegisterContent(
                    state = RegisterState(),
                    uiStrings = uiStrings,
                    onIntent = {},
                    onBackClick = {}
                )
            }
        }

        composeTestRule.onNodeWithText(uiStrings.welcome).assertIsDisplayed()
        composeTestRule.onNodeWithText(uiStrings.subtitle).assertIsDisplayed()
        composeTestRule.onNodeWithText(uiStrings.namePlaceholder).assertIsDisplayed()
        composeTestRule.onNodeWithText(uiStrings.emailPlaceholder).assertIsDisplayed()
        composeTestRule.onNodeWithText(uiStrings.signUp).assertIsDisplayed()
    }

    /**
     * Skenario: Validasi tombol Sign Up saat input masih kosong.
     * Ekspektasi: Tombol Sign Up tidak aktif (disabled) meskipun checkbox persetujuan sudah dicentang.
     */
    @Test
    fun registerButton_isDisabled_whenFieldsAreEmpty() {
        composeTestRule.setContent {
            MyApplicationTheme {
                RegisterContent(
                    state = RegisterState(isAgreed = true),
                    uiStrings = uiStrings,
                    onIntent = {},
                    onBackClick = {}
                )
            }
        }

        composeTestRule.onNodeWithText(uiStrings.signUp).assertIsNotEnabled()
    }

    /**
     * Skenario: Validasi tombol Sign Up saat input sudah terisi namun belum menyetujui syarat & ketentuan.
     * Ekspektasi: Tombol Sign Up tetap tidak aktif (disabled) karena persetujuan adalah syarat wajib.
     */
    @Test
    fun registerButton_isDisabled_whenTermsNotAgreed() {
        composeTestRule.setContent {
            MyApplicationTheme {
                RegisterContent(
                    state = RegisterState(
                        name = "User",
                        email = "user@mail.com",
                        password = "password",
                        isAgreed = false
                    ),
                    uiStrings = uiStrings,
                    onIntent = {},
                    onBackClick = {}
                )
            }
        }

        composeTestRule.onNodeWithText(uiStrings.signUp).assertIsNotEnabled()
    }

    /**
     * Skenario: Validasi tombol Sign Up saat seluruh syarat pendaftaran terpenuhi.
     * Ekspektasi: Tombol Sign Up aktif (enabled) dan siap untuk proses pendaftaran.
     */
    @Test
    fun registerButton_isEnabled_whenAllValid() {
        composeTestRule.setContent {
            MyApplicationTheme {
                RegisterContent(
                    state = RegisterState(
                        name = "User",
                        email = "user@mail.com",
                        password = "password",
                        isAgreed = true
                    ),
                    uiStrings = uiStrings,
                    onIntent = {},
                    onBackClick = {}
                )
            }
        }

        composeTestRule.onNodeWithText(uiStrings.signUp).assertIsEnabled()
    }
}
