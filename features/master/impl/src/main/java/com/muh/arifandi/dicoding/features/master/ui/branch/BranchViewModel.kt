/**
 * Created by Muh. Arifandi on 25/05/2026.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: BranchViewModel
 */
package com.muh.arifandi.dicoding.features.master.ui.branch

import com.muh.arifandi.dicoding.core.architecture.mvi.BaseViewModel
import com.muh.arifandi.dicoding.features.master.domain.model.BranchModel
import com.muh.arifandi.dicoding.features.master.ui.branch.state.BranchEffect
import com.muh.arifandi.dicoding.features.master.ui.branch.state.BranchIntent
import com.muh.arifandi.dicoding.features.master.ui.branch.state.BranchState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BranchViewModel @Inject constructor() :
    BaseViewModel<BranchState, BranchIntent, BranchEffect>(BranchState()) {

    init {
        loadInitialData()
    }

    override fun processIntent(intent: BranchIntent) {
        when (intent) {
            is BranchIntent.LoadBranches -> loadInitialData()
            is BranchIntent.SearchBranch -> filterBranches(intent.query)
            is BranchIntent.SelectBranch -> {
                setState { copy(selectedBranch = intent.branch) }
            }
            is BranchIntent.ClearSearch -> {
                setState {
                    copy(
                        searchQuery = "",
                        filteredBranches = branches
                    )
                }
            }
        }
    }

    private fun loadInitialData() {
        // Mock data based on design with added image URLs
        val mockBranches = listOf(
            BranchModel("1", "Bank 1656 Union Street", "1656 Union Street", "50 m", 40.7128, -74.0060, "https://images.unsplash.com/photo-1541339907198-e08756eaaaf8?w=500&q=80"),
            BranchModel("2", "Bank Secaucus", "Secaucus, NJ", "1,2 km", 40.7895, -74.0565, "https://images.unsplash.com/photo-1554469384-e58fac16e23a?w=500&q=80"),
            BranchModel("3", "Bank 1657 Riverside Drive", "1657 Riverside Drive", "5,3 km", 40.8448, -73.9412, "https://images.unsplash.com/photo-1486406146926-c627a92ad1ab?w=500&q=80"),
            BranchModel("4", "Bank Rutherford", "Rutherford, NJ", "70 m", 40.8265, -74.1068, "https://images.unsplash.com/photo-1577412647305-991150c7d163?w=500&q=80"),
            BranchModel("5", "Bank 1656 Union Street", "1656 Union Street", "30 m", 40.7128, -74.0060, "https://images.unsplash.com/photo-1541339907198-e08756eaaaf8?w=500&q=80")
        )
        setState {
            copy(
                branches = mockBranches,
                filteredBranches = mockBranches
            )
        }
    }

    private fun filterBranches(query: String) {
        val filtered = state.value.branches.filter {
            it.name.contains(query, ignoreCase = true) || it.address.contains(query, ignoreCase = true)
        }
        setState {
            copy(
                searchQuery = query,
                filteredBranches = filtered
            )
        }
    }
}
