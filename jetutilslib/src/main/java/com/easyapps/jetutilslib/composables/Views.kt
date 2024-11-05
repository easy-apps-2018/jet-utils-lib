package com.easyapps.jetutilslib.composables

import androidx.annotation.*
import androidx.compose.animation.*
import androidx.compose.animation.graphics.*
import androidx.compose.animation.graphics.res.*
import androidx.compose.animation.graphics.vector.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.painter.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.*

@Composable
fun BarColumn(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        content = content,
        modifier = modifier.animateContentSize().padding(start = 10.dp, top = 10.dp, end = 10.dp)
    )
}

/*@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationGraphicsApi::class)
fun Icon(
    modifier: Modifier = Modifier,
    iconSize: Dp = 30.dp,
    @DrawableRes icon: Int,
    visible: Boolean = true,
    checked: Boolean? = null,
    tint: Color = LocalContentColor.current,
    @StringRes contentDescription: Int? = null,
) {
    ScaleVisible(
        content = {
            val painter = if (checked != null)
                rememberAnimatedVectorPainter(
                    atEnd = checked,
                    animatedImageVector = AnimatedImageVector.animatedVectorResource(id = icon)
                )
            else
                painterResource(id = icon)
            if (contentDescription != null)
                TooltipBox(
                    tooltip = {
                        PlainTooltip(
                            contentColor = Color.White,
                            containerColor = MaterialTheme.colorScheme.secondary
                        ) {
                            Text(
                                fontWeight = FontWeight.Light,
                                text = onString(id = contentDescription)
                            )
                        }
                    },
                    content = {
                        Icon(
                            //tint = tint,
                            painter = painter,
                            modifier = Modifier.size(size = iconSize),
                            contentDescription = onString(id = contentDescription)
                        )
                    },
                    state = rememberTooltipState(),
                    positionProvider = TooltipDefaults.rememberTooltipPositionProvider()
                )
            else
                Icon(
                    //tint = tint,
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier.size(size = iconSize)
                )
        },
        visible = visible,
        modifier = modifier
    )
}*/

@Composable
fun Icon(
    iconSize: Dp = 28.dp,
    @DrawableRes icon: Int,
    visible: Boolean = true,
    checked: Boolean? = null,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    tint: Color = LocalContentColor.current,
) {
    ScaleVisible(
        content = {
            Icon(
                tint = tint,
                contentDescription = contentDescription,
                modifier = Modifier.size(size = iconSize),
                painter = rememberDrawable(icon = icon, checked = checked)
            )
        },
        visible = visible,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IconButton(
    @DrawableRes icon: Int,
    iconScale: Float = 1.5f,
    enabled: Boolean = true,
    visible: Boolean = true,
    checked: Boolean? = null,
    modifier: Modifier = Modifier,
    @StringRes contentDescription: Int,
    tint: Color = LocalContentColor.current,
    onClick: () -> Unit
) {
    ScaleVisible(visible = visible, modifier = modifier) {
        IconButton(
            content = {
                TooltipBox(
                    tooltip = {
                        PlainTooltip(
                            content = {
                                Text(
                                    style = MaterialTheme.typography.titleSmall,
                                    text = stringResource(id = contentDescription)
                                )
                            },
                            contentColor = Color.White,
                            containerColor = MaterialTheme.colorScheme.secondary
                        )
                    },
                    content = {
                        Icon(
                            tint = tint,
                            contentDescription = onString(id = contentDescription),
                            painter = rememberDrawable(icon = icon, checked = checked)
                        )
                    },
                    state = rememberTooltipState(),
                    positionProvider = TooltipDefaults.rememberTooltipPositionProvider()
                )
            },
            onClick = onClick,
            enabled = enabled,
            modifier = Modifier.scale(scale = iconScale),
            colors = IconButtonDefaults.iconButtonColors(contentColor = tint)
        )
    }
}

@Composable
fun AnimatedTitle(
    title: Any,
    visible: Boolean = true,
    modifier: Modifier = Modifier
) {
    ScaleVisible(visible = visible, modifier = modifier) {
        AnimatedContent(
            content = { value ->
                Text(
                    text = value,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.titleLarge
                )
            },
            state = if (title is Int)
                onString(id = title)
            else
                title.toString()
        )
    }
}

@Composable
fun FloatingActionButton(
    visible: Boolean,
    onClick: () -> Unit,
    @DrawableRes icon: Int,
    isFabSmall: Boolean = false,
    isFabLarge: Boolean = false,
    contentColor: Color = Color.White,
    containerColor: Color = MaterialTheme.colorScheme.secondary
) {
    ScaleVisible(visible = visible) {
        when {
            isFabSmall -> SmallFloatingActionButton(
                content = {
                    Icon(icon = icon)
                },
                onClick = onClick,
                contentColor = contentColor,
                containerColor = containerColor
            )

            isFabLarge -> LargeFloatingActionButton(
                content = {
                    Icon(icon = icon)
                },
                onClick = onClick,
                contentColor = contentColor,
                containerColor = containerColor
            )

            else ->
                FloatingActionButton(
                    content = {
                        Icon(icon = icon)
                    },
                    onClick = onClick,
                    contentColor = contentColor,
                    containerColor = containerColor
                )
        }
    }
}

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
private fun rememberDrawable(@DrawableRes icon: Int, checked: Boolean? = null): Painter {
    return if (checked != null)
        rememberAnimatedVectorPainter(
            atEnd = checked,
            animatedImageVector = AnimatedImageVector.animatedVectorResource(id = icon)
        )
    else
        painterResource(id = icon)
}