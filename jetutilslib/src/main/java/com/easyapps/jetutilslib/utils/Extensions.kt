package com.easyapps.jetutilslib.utils

import android.content.*
import android.content.pm.*
import android.net.*
import android.widget.*
import androidx.activity.*
import androidx.annotation.*
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.pager.*
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.*
import androidx.lifecycle.*
import androidx.navigation.*
import kotlinx.coroutines.*
import kotlin.coroutines.*

fun ViewModel.onLaunch(block: suspend CoroutineScope.() -> Unit) {
    this.viewModelScope.launch { block.invoke(this) }
}

fun ViewModel.onLaunchWithCtx(
    context: CoroutineContext = Dispatchers.IO,
    block: suspend CoroutineScope.() -> Unit
) {
    this.viewModelScope.launch { withContext(context = context) { block.invoke(this) } }
}

