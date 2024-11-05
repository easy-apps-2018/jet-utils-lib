package com.easyapps.jetutilslib.composables

import android.os.*
import androidx.activity.ComponentActivity
import androidx.annotation.*
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*
import kotlinx.coroutines.*
import kotlinx.parcelize.*

@Composable
fun ComponentActivity.MainScreen(
    modifier: Modifier,
    drawerState: DrawerState,
    isFabVisible: Boolean = true,
    isRailVisible: Boolean = false,
    drawerContentWidth: Dp = 300.dp,
    isBottomBarVisible: Boolean = true,
    drawerContentHeaderSize: Dp = 74.dp,
    drawerGesturesEnabled: Boolean = true,
    enablePermanentDrawer: Boolean = false,
    snackbarHost: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
    navRailContent: @Composable ColumnScope.() -> Unit,
    drawerContent: @Composable (ColumnScope.() -> Unit),
    bottomBarContent: @Composable RowScope.() -> Unit = {},
    bannerContent: @Composable (ColumnScope.() -> Unit) = {},
    navRailHeader: @Composable (ColumnScope.() -> Unit)? = null,
    containerColor: Color = MaterialTheme.colorScheme.background,
    floatingActionButtons: @Composable ColumnScope.() -> Unit = {},
    navRailContainerColor: Color = MaterialTheme.colorScheme.surface,
    drawerContainerColor: Color = MaterialTheme.colorScheme.background,
    permanentNavDrawerContainerColor: Color = MaterialTheme.colorScheme.surface
) {
    Column(
        content = {
            SlideInVisible(
                content = {
                    PermanentNavigationDrawer(
                        content = {
                            Scaffold(
                                content = content,
                                snackbarHost = snackbarHost,
                                floatingActionButton = {
                                    ScaleVisible(visible = isFabVisible) {
                                        Column(
                                            content = floatingActionButtons,
                                            verticalArrangement = Arrangement.spacedBy(10.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        )
                                    }
                                },
                                containerColor = containerColor,
                                modifier = Modifier.fillMaxSize(),
                            )
                        },
                        drawerContent = {
                            Column(
                                content = {
                                    Spacer(modifier = Modifier.size(size = drawerContentHeaderSize))
                                    drawerContent.invoke(this)
                                },
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(width = drawerContentWidth)
                                    .onVerticalScroll()
                                    .systemBarsPadding()
                                    .background(color = permanentNavDrawerContainerColor),
                                verticalArrangement = Arrangement.spacedBy(space = 4.dp)
                            )
                        },
                        modifier = Modifier.background(color = permanentNavDrawerContainerColor)
                    )
                },
                visible = enablePermanentDrawer,
                modifier = Modifier.weight(weight = 1f)
            )

            if (!enablePermanentDrawer)
                ModalNavigationDrawer(
                    content = {
                        Scaffold(
                            content = { paddingValues ->
                                Row(
                                    content = {
                                        SlideOutVisible(
                                            content = {
                                                NavigationRail(
                                                    header = {
                                                        Column {
                                                            Spacer(modifier = Modifier.padding(top = 4.dp))
                                                            navRailHeader?.invoke(this)
                                                        }
                                                    },
                                                    content = {
                                                        Column(
                                                            content = navRailContent,
                                                            modifier = Modifier.onVerticalScroll(),
                                                            horizontalAlignment = Alignment.CenterHorizontally
                                                        )
                                                    },
                                                    modifier = Modifier.fillMaxHeight(),
                                                    containerColor = navRailContainerColor,
                                                )
                                            },
                                            visible = isRailVisible,
                                            modifier = Modifier.fillMaxHeight()
                                        )
                                        content.invoke(paddingValues)
                                    },
                                    modifier = Modifier.fillMaxSize()
                                )
                            },
                            bottomBar = {
                                SlideInVisible(visible = isBottomBarVisible) {
                                    NavigationBar(
                                        content = bottomBarContent,
                                        containerColor = containerColor,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            },
                            snackbarHost = snackbarHost,
                            floatingActionButton = {
                                ScaleVisible(visible = isFabVisible) {
                                    Column(
                                        content = floatingActionButtons,
                                        verticalArrangement = Arrangement.spacedBy(10.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    )
                                }
                            },
                            containerColor = containerColor,
                            modifier = Modifier.fillMaxSize(),
                        )
                    },
                    drawerContent = {
                        ModalDrawerSheet(
                            content = {
                                Column(modifier = Modifier.onVerticalScroll()) {
                                    Spacer(modifier = Modifier.size(size = drawerContentHeaderSize))
                                    drawerContent.invoke(this)
                                }
                            },
                            drawerContainerColor = drawerContainerColor,
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(width = drawerContentWidth)
                                .systemBarsPadding()
                        )
                    },
                    drawerState = drawerState,
                    gesturesEnabled = drawerGesturesEnabled,
                    modifier = Modifier.weight(weight = 1f)
                )
            bannerContent.invoke(this)
        },
        modifier = modifier.fillMaxSize().animateContentSize()
    )
}


@Composable
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
fun ComponentActivity.isExpanded(boxScope: BoxWithConstraintsScope): Boolean {
    val windowSize = calculateWindowSizeClass(this)
    val expanded = windowSize.widthSizeClass == WindowWidthSizeClass.Expanded
    return expanded && boxScope.maxWidth >= 900.dp
}

@Composable
fun rememberStateOfDrawer(initialValue: DrawerValue = DrawerValue.Closed): DrawerState {
    return rememberDrawerState(initialValue = initialValue)
}

@Parcelize
data class NavItem(
    val mode: String,
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val visible: Boolean = true,
    val selected: Boolean = false
) : Parcelable


@Composable
fun DrawerItem(
    navItem: NavItem,
    selected: Boolean,
    drawerItemIconSize: Dp = 28.dp,
    drawerItemHorizontalPadding: Dp = 16.dp,
    selectedIconColor: Color = MaterialTheme.colorScheme.secondary,
    selectedContainerColor: Color = selectedIconColor.copy(alpha = 0.1f),
    unselectedContainerColor: Color = MaterialTheme.colorScheme.background,
    onClick: suspend CoroutineScope.() -> Unit
) {
    if (navItem.visible) {
        val scope = rememberCoroutineScope()
        NavigationDrawerItem(
            icon = {
                Icon(
                    icon = navItem.icon,
                    iconSize = drawerItemIconSize
                )
            },
            label = {
                Text(
                    text = onString(id = navItem.title),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Normal)
                )
            },
            onClick = {
                scope.launch { onClick.invoke(this) }
            },
            selected = selected,
            modifier = Modifier.padding(horizontal = drawerItemHorizontalPadding),
            colors = NavigationDrawerItemDefaults.colors(
                selectedIconColor = selectedIconColor,
                selectedContainerColor = selectedContainerColor,
                unselectedContainerColor = unselectedContainerColor
            )
        )
    }
}

@Composable
fun RailItem(
    navItem: NavItem,
    selected: Boolean,
    onClick: suspend CoroutineScope.() -> Unit,
    selectedIconColor: Color = MaterialTheme.colorScheme.secondary
) {
    if (navItem.visible) {
        val scope = rememberCoroutineScope()
        NavigationRailItem(
            icon = {
                Icon(icon = navItem.icon, iconSize = 24.dp)
            },
            onClick = {
                scope.launch { onClick.invoke(this) }
            },
            selected = selected,
            colors = NavigationRailItemDefaults.colors(
                selectedIconColor = selectedIconColor,
                indicatorColor = selectedIconColor.copy(alpha = 0.1f)
            )
        )
    }
}
