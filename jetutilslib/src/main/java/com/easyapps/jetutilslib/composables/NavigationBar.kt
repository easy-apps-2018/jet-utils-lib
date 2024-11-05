package com.easyapps.jetutilslib.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.*

@Composable
fun NavigationBar(
    visible: Boolean,
    content: @Composable RowScope.() -> Unit,
    background: Color = MaterialTheme.colorScheme.background,
) {

    SlideInVisible(visible = visible) {
        NavigationBar(
            content = content,
            containerColor = background,
            contentColor = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun RowScope.BarItem(
    navItem: NavItem,
    selected: Boolean,
    onClick: () -> Unit,
    restTextIconColors: Color = LocalContentColor.current,
    disabledIconColor: Color = MaterialTheme.colorScheme.secondary,
    selectedIconColor: Color = MaterialTheme.colorScheme.secondary
) {
    NavigationBarItem(
        icon = {
            Icon(icon = navItem.icon)
        },
        label = {
            Text(
                maxLines = 1,
                fontSize = 14.sp,
                overflow = TextOverflow.Ellipsis,
                text = stringResource(id = navItem.title),
                style = MaterialTheme.typography.labelSmall
            )
        },
        onClick = onClick,
        enabled = !selected,
        selected = selected,
        colors = NavigationBarItemDefaults.colors(
            disabledIconColor = disabledIconColor,
            selectedIconColor = selectedIconColor,
            disabledTextColor = restTextIconColors,
            selectedTextColor = restTextIconColors,
            unselectedIconColor = restTextIconColors,
            unselectedTextColor = restTextIconColors,
            indicatorColor = selectedIconColor.copy(alpha = 0.1f)
        )
    )
}