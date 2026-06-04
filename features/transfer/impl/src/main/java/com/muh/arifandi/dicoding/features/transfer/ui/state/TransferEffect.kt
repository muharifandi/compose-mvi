package com.muh.arifandi.dicoding.features.transfer.ui.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiEffect

sealed interface TransferEffect : UiEffect {
    data object ShowSuccess : TransferEffect
    data object NavigateToConfirm : TransferEffect
}
