package com.muh.arifandi.dicoding.core.ui.designsystem.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.ui.tooling.preview.Preview
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme

/**
 * Komponen Search Bar siap pakai untuk Saka Design System.
 * Mengelola state internal untuk kata kunci pencarian.
 *
 * @param onSearch Callback yang dipanggil setiap kali kata kunci berubah.
 * @param modifier Modifier untuk pengaturan margin/padding luar.
 * @param placeholder Teks petunjuk pencarian.
 */
@Composable
fun SakaSearchBar(
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Search news..."
) {
    var query by rememberSaveable { mutableStateOf("") }

    SakaSearchField(
        value = query,
        onValueChange = { 
            query = it
            onSearch(it)
        },
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        placeholder = placeholder
    )
}

@Preview(showBackground = true)
@Composable
private fun SakaSearchBarPreview() {
    MyApplicationTheme {
        SakaSearchBar(onSearch = {})
    }
}
