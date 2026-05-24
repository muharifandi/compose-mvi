/**
 * Created by Muh. Arifandi on 25/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:master:impl
 * File : MasterIntent.kt
 *
 * Description:
 * Kumpulan intent pengguna pada layar Master, seperti memilih tab navigasi.
 */
package com.muh.arifandi.dicoding.features.master.ui.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiIntent

sealed interface MasterIntent : UiIntent {
    data object LoadInitialData : MasterIntent
    data class SelectTab(val tab: MasterTab) : MasterIntent
}
