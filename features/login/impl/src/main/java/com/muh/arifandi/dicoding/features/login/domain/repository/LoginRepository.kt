/**
 * Created by Muh. Arifandi on 25/05/26.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: LoginRepository
 */
package com.muh.arifandi.dicoding.features.login.domain.repository

interface LoginRepository {
    suspend fun login(email: String, password: String): Result<Unit>
}
