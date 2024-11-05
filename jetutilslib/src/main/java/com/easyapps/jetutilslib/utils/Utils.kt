package com.easyapps.jetutilslib.utils

import android.os.*
import androidx.compose.runtime.saveable.*
import androidx.compose.ui.graphics.*
import androidx.core.app.*
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

val currentTime: Long
    get() = System.currentTimeMillis()

fun onHandler(delay: Long, onPostDelayed: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({ onPostDelayed.invoke() }, delay)
}

val ColorSaver = run {
    val redKey = "red"
    val blueKey = "blue"
    val greenKey = "green"
    mapSaver(
        save = { color ->
            mapOf(redKey to color.red, greenKey to color.green, blueKey to color.blue)
        },
        restore = { map ->
            Color(
                red = map[redKey] as Float,
                blue = map[blueKey] as Float,
                green = map[greenKey] as Float
            )
        }
    )
}