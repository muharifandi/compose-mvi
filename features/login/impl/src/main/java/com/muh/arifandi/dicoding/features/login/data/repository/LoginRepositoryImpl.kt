/**
 * Created by Muh. Arifandi on 25/05/26.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: LoginRepositoryImpl
 */
package com.muh.arifandi.dicoding.features.login.data.repository

import com.muh.arifandi.dicoding.features.login.domain.repository.LoginRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor() : LoginRepository {
    override suspend fun login(email: String, password: String): Result<Unit> {
        // Simulasi API call
        delay(1000)
        return Result.success(Unit)
    }
}
