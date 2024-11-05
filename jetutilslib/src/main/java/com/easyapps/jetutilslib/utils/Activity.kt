package com.easyapps.jetutilslib.utils

import android.content.pm.*
import androidx.activity.*
import androidx.annotation.*
import androidx.compose.material3.windowsizeclass.*
import androidx.compose.runtime.*
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.*

@Composable
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
fun ComponentActivity.isCompact(): Boolean {
    val windowSize = calculateWindowSizeClass(this)
    return windowSize.widthSizeClass == WindowWidthSizeClass.Compact
            || windowSize.heightSizeClass == WindowHeightSizeClass.Compact
}

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

fun ComponentActivity.onEdgeToEdge(@ColorInt color: Int) {
    this.enableEdgeToEdge(
        statusBarStyle = SystemBarStyle.light(
            scrim = color,
            darkScrim = color
        ),
        navigationBarStyle = SystemBarStyle.light(
            scrim = color,
            darkScrim = color
        )
    )
}