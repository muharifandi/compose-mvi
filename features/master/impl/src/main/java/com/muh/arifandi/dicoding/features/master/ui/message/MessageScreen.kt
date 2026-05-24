package com.muh.arifandi.dicoding.features.master.ui.message

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme
/**
 * Created by Muh. Arifandi on 25/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:master:impl
 * File : MasterScreen.kt
 *
 * Description:
 * Container utama aplikasi yang mengelola navigasi Bottom Bar.
 */

@Composable
fun MessageScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Message Screen", style = SakaTheme.typography.title2)
    }
}
