package com.easyapps.jetutilslib.utils

import android.content.pm.*
import androidx.activity.*
import androidx.compose.runtime.*
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.*

fun ComponentActivity.onScreenLock(isLock: Boolean) {
    this.requestedOrientation = if (isLock)
        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    else
        ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
}

fun ComponentActivity.initSplashScreen(delay: Long = 800) {
    var isSplash = true
    onHandler(delay) { isSplash = false }
    this.installSplashScreen().setKeepOnScreenCondition { isSplash }
}

fun ComponentActivity.enableDecorFitsSystemWindows(decorFitsSystemWindows: Boolean) {
    WindowCompat.setDecorFitsSystemWindows(this.window, decorFitsSystemWindows)
}

@Composable
fun ComponentActivity.MainScreen() {

}
