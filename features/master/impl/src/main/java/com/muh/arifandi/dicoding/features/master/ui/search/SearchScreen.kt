/**
 * Created by Muh. Arifandi on 25/05/26.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: SearchScreen
 */
package com.muh.arifandi.dicoding.features.master.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaNavigationBar
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaScaffold
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme
import com.muh.arifandi.dicoding.features.master.domain.model.ItemSearch
import com.muh.arifandi.dicoding.features.master.ui.search.state.SearchState
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onNavigateByRoute: (String) -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    SearchScreenContent(
        state = state,
        onNavigateByRoute = onNavigateByRoute,
        onBackClick = onBackClick
    )
}


@Composable
private fun SearchScreenContent(
    state: SearchState,
    modifier: Modifier = Modifier,
    onNavigateByRoute: (String) -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    SakaScaffold(
        modifier = modifier.fillMaxSize(),
        // Menggunakan background off-white agar kartu putih terlihat kontras (sesuai desain)
        containerColor = Color(0xFFF8F9FA),
        topBar = {
            SakaNavigationBar(
                title = "Search",
                onBackClick = onBackClick,
                showIcon = true, // Tampilkan icon back sesuai desain
                modifier = Modifier
                    .background(color = Color.White)
                    .padding(bottom = 4.dp)
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { Spacer(modifier = Modifier.height(16.dp)) }

            items(state.searchItems) { item ->
                ItemSearch(
                    title = item.title,
                    description = item.description,
                    imageRes = item.imageRes,
                    onClick = {
                        item.targetRoute?.let { onNavigateByRoute(it) }
                    }
                )
            }

            item { Spacer(modifier = Modifier.height(32.dp)) }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchScreenPreview() {
    MyApplicationTheme {
        SearchScreenContent(
            state = SearchState()
        )
    }
}

