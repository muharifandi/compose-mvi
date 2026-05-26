/**
 * Created by Muh. Arifandi on 25/05/26.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: ForgotpasswordRepository
 */
package com.muh.arifandi.dicoding.features.forgotpassword.domain.repository

interface ForgotpasswordRepository {
    suspend fun sendOtp(phoneNumber: String): Result<Unit>
    suspend fun verifyOtp(code: String): Result<Unit>
}
