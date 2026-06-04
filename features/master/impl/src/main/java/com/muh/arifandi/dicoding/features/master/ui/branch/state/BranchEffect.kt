/**
 * Created by Muh. Arifandi on 25/05/2026.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: BranchEffect
 */
package com.muh.arifandi.dicoding.features.master.ui.branch.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiEffect

sealed interface BranchEffect : UiEffect {
    data class ShowError(val message: String) : BranchEffect
    data object NavigateBack : BranchEffect
}
