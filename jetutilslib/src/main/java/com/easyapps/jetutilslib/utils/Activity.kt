package com.easyapps.jetutilslib.utils

import android.content.pm.*
import androidx.activity.*
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.*

fun ComponentActivity.onScreenLock(isLock: Boolean) {
    this.requestedOrientation = if (isLock)
        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    else
        ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
}

fun ComponentActivity.enableSplashScreen(delay: Long = 800) {
    var isSplash = true
    onHandler(delay) { isSplash = false }
    this.installSplashScreen().setKeepOnScreenCondition { isSplash }
}

fun ComponentActivity.enableDecorFitsSystemWindows(fits: Boolean) {
    WindowCompat.setDecorFitsSystemWindows(this.window, fits)
}
