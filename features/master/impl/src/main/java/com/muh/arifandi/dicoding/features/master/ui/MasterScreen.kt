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

package com.muh.arifandi.dicoding.features.master.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaScaffold
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaTabBar
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaTabItem
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme
import com.muh.arifandi.dicoding.features.master.ui.home.HomeScreen
import com.muh.arifandi.dicoding.features.master.ui.message.MessageScreen
import com.muh.arifandi.dicoding.features.master.ui.search.SearchScreen
import com.muh.arifandi.dicoding.features.master.ui.settings.SettingsScreen
import com.muh.arifandi.dicoding.features.master.ui.state.MasterIntent
import com.muh.arifandi.dicoding.features.master.ui.state.MasterState
import com.muh.arifandi.dicoding.features.master.ui.state.MasterTab

@Composable
fun MasterScreen(
    viewModel: MasterViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    MasterContent(
        state = state,
        onIntent = { viewModel.processIntent(it) },
    )
}

@Composable
internal fun MasterContent(
    state: MasterState,
    onIntent: (MasterIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val items = listOf(
        SakaTabItem("Home", Icons.Outlined.Home),
        SakaTabItem("Search", Icons.Outlined.Search),
        SakaTabItem("Message", Icons.Outlined.Email),
        SakaTabItem("Settings", Icons.Outlined.Settings),
    )

    SakaScaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            SakaTabBar(
                items = items,
                selectedIndex = state.selectedTab.ordinal,
                onItemSelected = { index ->
                    onIntent(MasterIntent.SelectTab(MasterTab.entries[index]))
                },
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding()),
        ) {
            when (state.selectedTab) {
                MasterTab.HOME -> HomeScreen()
                MasterTab.SEARCH -> SearchScreen()
                MasterTab.MESSAGE -> MessageScreen()
                MasterTab.SETTINGS -> SettingsScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MasterScreenPreview() {
    MyApplicationTheme {
        MasterContent(
            state = MasterState(),
            onIntent = {},
        )
    }
}
