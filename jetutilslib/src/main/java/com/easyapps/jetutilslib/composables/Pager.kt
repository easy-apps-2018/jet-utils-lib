package com.easyapps.jetutilslib.composables

import androidx.compose.foundation.pager.*
import kotlinx.coroutines.*

fun PagerState.page(): Int {
    return this.currentPage
}

fun PagerState.page(index: Int = 0): Int {
    return this.currentPage + index
}

suspend fun PagerState.scroll(plus: Int, delay: Long = 0) {
    delay(delay)
    val value = if (this.page(plus) > -1)
        this.page(plus)
    else
        0
    this.animateScrollToPage(value)
}
