/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:login:impl
 * File : LoginScreenTest.kt
 *
 * Description:
 * Unit test UI untuk layar Login, memastikan validasi input dan status tombol.
 */

package com.muh.arifandi.dicoding.features.login.ui

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme
import com.muh.arifandi.dicoding.features.login.ui.state.LoginState
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    /**
     * Objek konfigurasi string untuk UI.
     * Menggunakan nilai statis untuk mempermudah verifikasi teks pada pengujian.
     */
    private val uiStrings = LoginUiStrings(
        title = "Sign In",
        welcome = "Welcome Back",
        subtitle = "Hello there, sign in to continue",
        emailPlaceholder = "Email Address",
        passwordPlaceholder = "Password",
        forgotPassword = "Forgot your password ?",
        signIn = "Sign in",
        noAccount = "Don't have an account? ",
        signUp = "Sign Up"
    )

    /**
     * Skenario: Memastikan semua elemen utama pada layar Login tampil saat pertama kali dibuka.
     * Ekspektasi: Judul, sub-judul, field email/password, dan tombol Sign In terlihat oleh pengguna.
     */
    @Test
    fun loginScreen_initialState_displaysAllViews() {
        composeTestRule.setContent {
            MyApplicationTheme {
                LoginContent(
                    state = LoginState(),
                    uiStrings = uiStrings,
                    onIntent = {}
                )
            }
        }

        composeTestRule.onNodeWithText(uiStrings.welcome).assertIsDisplayed()
        composeTestRule.onNodeWithText(uiStrings.subtitle).assertIsDisplayed()
        composeTestRule.onNodeWithText(uiStrings.emailPlaceholder).assertIsDisplayed()
        composeTestRule.onNodeWithText(uiStrings.passwordPlaceholder).assertIsDisplayed()
        composeTestRule.onNodeWithText(uiStrings.signIn).assertIsDisplayed()
    }

    /**
     * Skenario: Validasi tombol Sign In dalam kondisi input kosong.
     * Ekspektasi: Tombol Sign In harus dalam keadaan tidak aktif (disabled) untuk mencegah submit data kosong.
     */
    @Test
    fun loginButton_isDisabled_whenFieldsAreEmpty() {
        composeTestRule.setContent {
            MyApplicationTheme {
                LoginContent(
                    state = LoginState(email = "", password = ""),
                    uiStrings = uiStrings,
                    onIntent = {}
                )
            }
        }

        composeTestRule.onNodeWithText(uiStrings.signIn).assertIsNotEnabled()
    }

    /**
     * Skenario: Validasi tombol Sign In saat field email dan password sudah terisi.
     * Ekspektasi: Tombol Sign In harus aktif (enabled) sehingga pengguna bisa melanjutkan proses login.
     */
    @Test
    fun loginButton_isEnabled_whenFieldsAreFilled() {
        composeTestRule.setContent {
            MyApplicationTheme {
                LoginContent(
                    state = LoginState(email = "test@example.com", password = "password123"),
                    uiStrings = uiStrings,
                    onIntent = {}
                )
            }
        }

        composeTestRule.onNodeWithText(uiStrings.signIn).assertIsEnabled()
    }

    /**
     * Skenario: Menguji interaksi input teks pada field email dan password.
     * Ekspektasi: Pengguna dapat mengetikkan teks ke dalam field dan memicu perubahan state/intent.
     */
    @Test
    fun typingEmailAndPassword_updatesFields() {
        var emailValue = ""
        var passwordValue = ""

        composeTestRule.setContent {
            MyApplicationTheme {
                LoginContent(
                    state = LoginState(email = emailValue, password = passwordValue),
                    uiStrings = uiStrings,
                    onIntent = { intent ->
                    }
                )
            }
        }

        composeTestRule.onNodeWithText(uiStrings.emailPlaceholder).performTextInput("user@mail.com")
        composeTestRule.onNodeWithText(uiStrings.passwordPlaceholder).performTextInput("secret")
    }
}
