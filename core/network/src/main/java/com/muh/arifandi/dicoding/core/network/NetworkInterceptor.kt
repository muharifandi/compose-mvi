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
        
        val url = originalRequest.url.newBuilder()
            .addQueryParameter("apiKey", apiKey)
            .build()
        
        val newRequest = originalRequest.newBuilder()
            .url(url)
            .build()
        
        return chain.proceed(newRequest)
    }
}
