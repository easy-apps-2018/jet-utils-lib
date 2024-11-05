package com.easyapps.jetutilslib.composables

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.navigation.*
import androidx.navigation.compose.*
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
    val controller = rememberNavController()
    controller.addOnDestinationChangedListener { _, destination, _ ->
        onChanged.invoke(destination.route.toString())
    }
    return controller
}

fun onExitScaleOut(): ExitTransition {
    return scaleOut(animationSpec = tween())
}

fun onEnterScaleIn(): EnterTransition {
    return scaleIn(animationSpec = tween())
}

fun NavController.onNavigate(route: String) {
    this.navigate(route) {
        popUpTo(this@onNavigate.graph.id) {
            this.inclusive = true
        }
    }
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.onTarget(): String? {
    return this.targetState.destination.route
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.onInitial(): String? {
    return this.initialState.destination.route
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.onExitDown(): ExitTransition {
    return this.slideOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.Down,
        animationSpec = tween()
    )
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.onEnterUp(): EnterTransition {
    return slideIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.Up,
        animationSpec = tween()
    )
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.onExitLeft(): ExitTransition {
    return slideOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.Left,
        animationSpec = tween()
    )
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.onExitRight(): ExitTransition {
    return slideOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = tween()
    )
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.onEnterLeft(): EnterTransition {
    return slideIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.Left,
        animationSpec = tween()
    )
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.onEnterRight(): EnterTransition {
    return slideIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = tween()
    )
}