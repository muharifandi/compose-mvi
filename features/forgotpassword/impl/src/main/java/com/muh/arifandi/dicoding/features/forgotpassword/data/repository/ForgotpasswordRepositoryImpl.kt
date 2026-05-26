/**
 * Created by Muh. Arifandi on 25/05/26.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: ForgotpasswordRepositoryImpl
 */
package com.muh.arifandi.dicoding.features.forgotpassword.data.repository

import com.muh.arifandi.dicoding.features.forgotpassword.domain.repository.ForgotpasswordRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class ForgotpasswordRepositoryImpl @Inject constructor() : ForgotpasswordRepository {
    override suspend fun sendOtp(phoneNumber: String): Result<Unit> {
        delay(1500)
        return Result.success(Unit)
    }

    override suspend fun verifyOtp(code: String): Result<Unit> {
        delay(1000)
        return Result.success(Unit)
    }
}
