/**
 * Created by Muh. Arifandi on 25/05/2026
 * Email : arif76440@gmail.com
 * Project : SakaAndroid
 * File : MasterRepository.kt
 */

package com.muh.arifandi.dicoding.features.master.domain.repository

import com.muh.arifandi.dicoding.features.master.domain.model.CreditCardInfo
import com.muh.arifandi.dicoding.features.master.domain.model.MasterMenuItem
import kotlinx.coroutines.flow.Flow

interface MasterRepository {
    fun getCreditCards(): Flow<List<CreditCardInfo>>
    fun getMenuItems(cardIndex: Int): Flow<List<MasterMenuItem>>
}
