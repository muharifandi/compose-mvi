/**
 * Created by Muh. Arifandi on 25/05/26.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: RegisterRepositoryImpl
 */
package com.muh.arifandi.dicoding.features.register.data.repository

import com.muh.arifandi.dicoding.features.register.domain.repository.RegisterRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor() : RegisterRepository {
    override suspend fun register(name: String, email: String, password: String): Result<Unit> {
        delay(1500)
        return Result.success(Unit)
    }
}
