package com.easyapps.jetutilslib.utils

import android.content.*
import android.net.*
import android.widget.*
import androidx.activity.*
import androidx.annotation.*


fun Context.onPlay() {
    try {
        this.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("market://details?id=${this.packageName}")
            )
        )
    } catch (e: ActivityNotFoundException) {
        this.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=${this.packageName}")
            )
        )
    }
}

fun Context.onExit() {
    (this as ComponentActivity).moveTaskToBack(true)
}

fun Context.onToast(text: String?) {
    if (text != null)
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.getVersionName(): String {
    return try {
        this.packageManager.getPackageInfo(this.packageName, 0).versionName ?: EMPTY
    } catch (_: Exception) {
        EMPTY
    }
}

fun Context.onAssets(name: String): String {
    return this.assets.open(name).bufferedReader().use { it.readText() }
}

fun Context.onToast(@StringRes text: Int?) {
    if (text != null)
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.onShare(@StringRes appName: Int) {
    val string =
        this.resources.getString(appName) + ": https://play.google.com/store/apps/details?id=${this.packageName}"
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, string)
        type = "text/plain"
    }
    this.startActivity(Intent.createChooser(sendIntent, null))
}

fun Context.onFeedback(@StringRes message: Int) {
    val email = Intent(Intent.ACTION_SEND).apply {
        type = "text/email"
        putExtra(Intent.EXTRA_EMAIL, arrayOf("easy.app.2018@gmail.com"))
        putExtra(Intent.EXTRA_SUBJECT, this@onFeedback.onString(message))
        putExtra(Intent.EXTRA_TEXT, EMPTY)
    }
    try {
        this.startActivity(
            Intent.createChooser(email, this@onFeedback.onString(message) + ":")
        )
    } catch (_: ActivityNotFoundException) { }
}

fun Context.onString(@StringRes res: Int): String {
    return this.resources.getString(res)
}