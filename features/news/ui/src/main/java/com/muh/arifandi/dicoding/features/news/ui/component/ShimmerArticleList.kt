/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:news:ui
 * File : ShimmerArticleList.kt
 *
 * Description:
 * Komponen loading placeholder (shimmer) untuk daftar berita.
 */

package com.muh.arifandi.dicoding.features.news.ui.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable

@Composable
fun ShimmerArticleList() {

    LazyColumn {

        items(6) {

            ShimmerArticleItem()
        }
    }
}
