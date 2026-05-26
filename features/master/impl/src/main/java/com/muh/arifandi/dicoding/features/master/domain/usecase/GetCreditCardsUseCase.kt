/**
 * Created by Muh. Arifandi on 25/05/2026
 * Email : arif76440@gmail.com
 * Project : SakaAndroid
 * File : GetCreditCardsUseCase.kt
 */

package com.muh.arifandi.dicoding.features.master.domain.usecase

import com.muh.arifandi.dicoding.features.master.domain.model.CreditCardInfo
import com.muh.arifandi.dicoding.features.master.domain.repository.MasterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCreditCardsUseCase @Inject constructor(
    private val repository: MasterRepository
) {
    operator fun invoke(): Flow<List<CreditCardInfo>> = repository.getCreditCards()
}
