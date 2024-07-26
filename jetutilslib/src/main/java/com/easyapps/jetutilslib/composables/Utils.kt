package com.easyapps.jetutilslib.composables

import androidx.annotation.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.*
import androidx.navigation.*
import com.easyapps.jetutilslib.R

@Composable
fun rememberNavController(onChanged: (value: String) -> Unit): NavHostController {
    val controller = androidx.navigation.compose.rememberNavController()
    controller.addOnDestinationChangedListener { _, destination, _ ->
        onChanged.invoke(destination.route.toString())
    }
    return controller
}

@Composable
fun onPlural(@PluralsRes res: Int?, count: Int, vararg formatArgs: Any): String {
    return pluralStringResource(id = res ?: R.string.empty, count = count, formatArgs = formatArgs)
}

@Composable
fun onString(@StringRes id: Int): String {
    return stringResource(id = id)
}

@Composable
fun onString(@StringRes id: Int?, vararg formatArgs: Any): String {
    return stringResource(id = id ?: R.string.empty, formatArgs = formatArgs)
}