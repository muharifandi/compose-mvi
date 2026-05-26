/**
 * Created by Muh. Arifandi on 25/05/26.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: RegisterRepository
 */
package com.muh.arifandi.dicoding.features.register.domain.repository

interface RegisterRepository {
    suspend fun register(name: String, email: String, password: String): Result<Unit>
}
