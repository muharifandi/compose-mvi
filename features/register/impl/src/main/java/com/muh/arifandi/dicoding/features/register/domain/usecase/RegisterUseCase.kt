/**
 * Created by Muh. Arifandi on 25/05/26.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: RegisterUseCase
 */
package com.muh.arifandi.dicoding.features.register.domain.usecase

import com.muh.arifandi.dicoding.features.register.domain.repository.RegisterRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: RegisterRepository
) {
    suspend operator fun invoke(name: String, email: String, password: String): Result<Unit> {
        return repository.register(name, email, password)
    }
}
