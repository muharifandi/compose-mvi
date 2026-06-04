/**
 * Created by Muh. Arifandi on 25/05/2026.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: BranchIntent
 */
package com.muh.arifandi.dicoding.features.master.ui.branch.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiIntent
import com.muh.arifandi.dicoding.features.master.domain.model.BranchModel

sealed interface BranchIntent : UiIntent {
    data object LoadBranches : BranchIntent
    data class SearchBranch(val query: String) : BranchIntent
    data class SelectBranch(val branch: BranchModel) : BranchIntent
    data object ClearSearch : BranchIntent
}
