/**
 * Created by Muh. Arifandi on 24/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:forgotpassword:api
 * File : ForgotpasswordDestinations.kt
 */
package com.muh.arifandi.dicoding.features.forgotpassword.api

import kotlinx.serialization.Serializable

@Serializable
sealed interface ForgotpasswordDestinations {
    @Serializable
    data object Request : ForgotpasswordDestinations

    @Serializable
    data object ChangePassword : ForgotpasswordDestinations

    @Serializable
    data object Success : ForgotpasswordDestinations
}
