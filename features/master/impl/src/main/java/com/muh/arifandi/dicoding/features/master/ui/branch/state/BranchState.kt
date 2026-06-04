/**
 * Created by Muh. Arifandi on 25/05/2026.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: BranchState
 */
package com.muh.arifandi.dicoding.features.master.ui.branch.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiState
import com.muh.arifandi.dicoding.features.master.domain.model.BranchModel

data class BranchState(
    val isLoading: Boolean = false,
    val branches: List<BranchModel> = emptyList(),
    val filteredBranches: List<BranchModel> = emptyList(),
    val searchQuery: String = "",
    val selectedBranch: BranchModel? = null
) : UiState
