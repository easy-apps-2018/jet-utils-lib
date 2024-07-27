package com.easyapps.jetutilslib.composables

import androidx.annotation.*
import androidx.compose.animation.graphics.*
import androidx.compose.animation.graphics.res.*
import androidx.compose.animation.graphics.vector.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationGraphicsApi::class)
fun Icon(
    modifier: Modifier = Modifier,
    iconSize: Dp = 30.dp,
    @DrawableRes icon: Int,
    visible: Boolean = true,
    checked: Boolean? = null,
    @StringRes contentDescription: Int? = null,
    tint: Color = LocalContentColor.current
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
                            tint = tint,
                            painter = painter,
                            modifier = Modifier.size(size = iconSize),
                            contentDescription = onString(id = contentDescription)
                        )
                    },
                    state = rememberTooltipState(),
                    positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider()
                )
            else
                Icon(
                    tint = tint,
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier.size(size = iconSize)
                )
        },
        visible = visible,
        modifier = modifier
    )
}

@Composable
fun IconButton(
    modifier: Modifier = Modifier,
    iconSize: Dp = 30.dp,
    @DrawableRes icon: Int,
    enabled: Boolean = true,
    visible: Boolean = true,
    checked: Boolean? = null,
    @StringRes contentDescription: Int,
    tint: Color = LocalContentColor.current,
    onClick: () -> Unit
) {
    ScaleVisible(visible = visible, modifier = modifier) {
        IconButton(
            content = {
                Icon(
                    tint = tint,
                    icon = icon,
                    checked = checked,
                    iconSize = iconSize,
                    contentDescription = contentDescription
                )
            },
            onClick = onClick,
            enabled = enabled,
        )
    }
}