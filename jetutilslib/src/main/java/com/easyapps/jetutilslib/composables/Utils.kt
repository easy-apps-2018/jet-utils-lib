package com.easyapps.jetutilslib.composables

import androidx.annotation.*
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.*
import androidx.compose.runtime.snapshots.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.*
import androidx.navigation.*
import androidx.navigation.compose.*
import com.easyapps.jetutilslib.R
import com.easyapps.jetutilslib.utils.*

@Composable
fun NavGraph(
    modifier: Modifier,
    startRoute: String = HOME,
    controller: NavHostController,
    graphBuilder: NavGraphBuilder.() -> Unit
) {
    NavHost(
        modifier = modifier,
        builder = graphBuilder,
        navController = controller,
        startDestination = startRoute
    )
}

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


@Composable
fun ScaleVisible(
    visible: Boolean,
    modifier: Modifier = Modifier,
    duration: Int = 300,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    AnimatedVisibility(
        content = content,
        visible = visible,
        modifier = modifier,
        enter = scaleIn(tween(duration)),
        exit = scaleOut(tween(duration))
    )
}

@Composable
fun onAnimateColor(
    color: Color,
    alpha: Float = 1f,
    duration: Int = 300,
    label: String = EMPTY
): Color {
    val state by animateColorAsState(
        label = label,
        targetValue = color,
        animationSpec = tween(durationMillis = duration)
    )
    return state.copy(alpha)
}

@Composable
fun onAnimateFloat(
    value: Float,
    duration: Int = 300,
    label: String = EMPTY
): Float {
    val state by animateFloatAsState(
        label = label,
        targetValue = value,
        animationSpec = tween(durationMillis = duration)
    )
    return state
}

@Composable
fun onAnimateInt(
    value: Int,
    duration: Int = 300,
    label: String = EMPTY
): Int {
    val state by animateIntAsState(
        label = label,
        targetValue = value,
        animationSpec = tween(durationMillis = duration)
    )
    return state
}

@Composable
fun onAnimateDp(
    value: Dp,
    duration: Int = 300,
    label: String = EMPTY
): Dp {
    val state by animateDpAsState(
        label = label,
        targetValue = value,
        animationSpec = tween(durationMillis = duration)
    )
    return state
}

@Composable
fun <T : Any> rememberMutableStateListOf(vararg elements: T): SnapshotStateList<T> {
    return rememberSaveable(
        saver = listSaver(
            save = { stateList -> stateList.toList() },
            restore = { list -> list.toMutableStateList() }
        )
    ) {
        elements.toList().toMutableStateList()
    }
}

@Composable
fun <S> AnimatedContent(
    state: S,
    modifier: Modifier = Modifier,
    label: String = EMPTY,
    content: @Composable AnimatedContentScope.(targetState: S) -> Unit
) {
    AnimatedContent(
        label = label,
        content = content,
        modifier = modifier,
        targetState = state
    )
}