/**
 * Created by Muh. Arifandi on 25/05/26.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: ForgotpasswordUseCase
 */
package com.muh.arifandi.dicoding.features.forgotpassword.domain.usecase

import com.muh.arifandi.dicoding.features.forgotpassword.domain.repository.ForgotpasswordRepository
import javax.inject.Inject

class ForgotpasswordUseCase @Inject constructor(
    private val repository: ForgotpasswordRepository
) {
    suspend fun sendOtp(phoneNumber: String): Result<Unit> = repository.sendOtp(phoneNumber)
    suspend fun verifyOtp(code: String): Result<Unit> = repository.verifyOtp(code)
}
