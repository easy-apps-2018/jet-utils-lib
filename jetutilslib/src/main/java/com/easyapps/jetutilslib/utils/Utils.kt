package com.easyapps.jetutilslib.utils

import android.os.*
import androidx.core.app.*
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

val currentTime: Long
    get() = System.currentTimeMillis()

fun onHandler(delay: Long, onPostDelayed: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({ onPostDelayed.invoke() }, delay)
}

fun ComponentActivity.enableSplashScreen(delay: Long = 800L) {
    var isSplash = true
    onHandler(delay = delay) { isSplash = false }
    this.installSplashScreen().setKeepOnScreenCondition { isSplash }
}