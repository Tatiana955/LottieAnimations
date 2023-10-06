package com.example.animations.ui.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.animations.domain.util.NavItems
import com.example.animations.ui.theme.LightGray
import com.example.animations.ui.theme.Yellow
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Parabolic
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius

@Composable
fun AppBottomBar(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val navBarItems = remember { listOf(NavItems.Home, NavItems.List, NavItems.Chameleon) }

    var selectedIndex by remember { mutableIntStateOf(0) }

    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = LightGray,
        targetValue = Yellow,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 30000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "animated color of bottom bar"
    )

    AnimatedNavigationBar(
        modifier = modifier
            .height(64.dp),
        selectedIndex = selectedIndex,
        cornerRadius = shapeCornerRadius(
            cornerRadius = 34.dp
        ),
        ballAnimation = Parabolic(tween(300)),
        indentAnimation = Height(tween(300)),
        barColor = color,
        ballColor = color
    ) {
        navBarItems.forEach { item ->
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .noRippleClickable {
                        selectedIndex = item.ordinal
                        navController.navigate(item.route)
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = modifier
                        .size(26.dp),
                    imageVector = ImageVector.vectorResource(item.icon),
                    contentDescription = item.description,
                    tint = if (selectedIndex == item.ordinal) Color.White else Color.Gray
                )
            }
        }
    }
}

private fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        onClick()
    }
}