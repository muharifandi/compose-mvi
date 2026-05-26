/**
 * Created by Muh. Arifandi on 25/05/2026
 * Email : arif76440@gmail.com
 * Project : SakaAndroid
 * File : GetMenuItemsUseCase.kt
 */

package com.muh.arifandi.dicoding.features.master.domain.usecase

import com.muh.arifandi.dicoding.features.master.domain.model.MasterMenuItem
import com.muh.arifandi.dicoding.features.master.domain.repository.MasterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMenuItemsUseCase @Inject constructor(
    private val repository: MasterRepository
) {
    operator fun invoke(cardIndex: Int): Flow<List<MasterMenuItem>> = repository.getMenuItems(cardIndex)
}
