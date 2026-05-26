/**
 * Created by Muh. Arifandi on 25/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:master:impl
 * File : MasterMenuItem.kt
 */

package com.muh.arifandi.dicoding.features.master.domain.model

enum class MenuType {
    ACCOUNT, TRANSFER, WITHDRAW, MOBILE, BILLS, SAVINGS, CREDIT, QR, TOP_UP, MORE
}

data class MasterMenuItem(
    val type: MenuType,
    val title: String,
    val colorHex: String
)
