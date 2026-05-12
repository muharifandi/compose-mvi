package com.muh.arifandi.dicoding.core.ui.designsystem.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme

@Composable
fun MainApp(
    content: @Composable () -> Unit
) {
    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}
