/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:forgotpassword:impl
 * File : ChangePasswordScreenTest.kt
 *
 * Description:
 * Pengujian UI untuk validasi konfirmasi password pada layar Change Password.
 */

package com.muh.arifandi.dicoding.features.forgotpassword.ui

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme
import com.muh.arifandi.dicoding.features.forgotpassword.ui.changepassword.ChangePasswordContent
import com.muh.arifandi.dicoding.features.forgotpassword.ui.changepassword.ChangePasswordUiStrings
import com.muh.arifandi.dicoding.features.forgotpassword.ui.changepassword.state.ChangePasswordState
import org.junit.Rule
import org.junit.Test

class ChangePasswordScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val uiStrings = ChangePasswordUiStrings(
        title = "Change password",
        typeNew = "Type your new password",
        confirm = "Confirm password",
        placeholder = "**********",
        button = "Change password",
        errorMismatch = "Password konfirmasi tidak cocok"
    )

    /**
     * Skenario: Menampilkan error saat password dan konfirmasi tidak cocok.
     */
    @Test
    fun changePassword_showsError_whenPasswordsMismatch() {
        composeTestRule.setContent {
            MyApplicationTheme {
                ChangePasswordContent(
                    state = ChangePasswordState(
                        newPassword = "password123",
                        confirmPassword = "password321"
                    ),
                    uiStrings = uiStrings,
                    onIntent = {}
                )
            }
        }

        composeTestRule.onNodeWithText(uiStrings.errorMismatch).assertIsDisplayed()
    }

    /**
     * Skenario: Tombol Change Password tidak aktif saat password tidak cocok.
     */
    @Test
    fun changePasswordButton_isDisabled_whenPasswordsMismatch() {
        composeTestRule.setContent {
            MyApplicationTheme {
                ChangePasswordContent(
                    state = ChangePasswordState(
                        newPassword = "password123",
                        confirmPassword = "password321"
                    ),
                    uiStrings = uiStrings,
                    onIntent = {}
                )
            }
        }

        composeTestRule.onNodeWithText(uiStrings.button).assertIsNotEnabled()
    }

    /**
     * Skenario: Tombol Change Password aktif saat password cocok.
     */
    @Test
    fun changePasswordButton_isEnabled_whenPasswordsMatch() {
        composeTestRule.setContent {
            MyApplicationTheme {
                ChangePasswordContent(
                    state = ChangePasswordState(
                        newPassword = "password123",
                        confirmPassword = "password123"
                    ),
                    uiStrings = uiStrings,
                    onIntent = {}
                )
            }
        }

        composeTestRule.onNodeWithText(uiStrings.button).assertIsEnabled()
    }
}
