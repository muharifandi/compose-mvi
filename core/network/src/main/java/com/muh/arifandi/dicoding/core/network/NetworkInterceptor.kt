/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : core:network
 * File : NetworkInterceptor.kt
 *
 * Description:
 * Interceptor untuk menyuntikkan API Key ke setiap request jaringan secara otomatis.
 */

package com.muh.arifandi.dicoding.core.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Named

class NetworkInterceptor @Inject constructor(
    @Named("apiKey") private val apiKey: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        
        if (apiKey.isBlank()) {
            android.util.Log.e("NetworkInterceptor", "CRITICAL: News API Key is empty or blank!")
        }

        // NewsAPI supports both X-Api-Key header and Authorization header
        val newRequest = originalRequest.newBuilder()
            .removeHeader("X-Api-Key")
            .addHeader("X-Api-Key", apiKey)
            .build()
        
        return chain.proceed(newRequest)
    }
}
