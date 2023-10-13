package com.example.animations.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.animations.domain.util.NavItems
import com.example.animations.ui.theme.Blue
import com.example.animations.ui.theme.Green
import com.example.animations.ui.theme.LightGray
import com.example.animations.ui.theme.Lilac
import com.example.animations.ui.theme.Orange
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AppDrawer(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState,
    navController: NavController
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Header(modifier = modifier)

        DrawerItems(
            modifier = modifier,
            scaffoldState = scaffoldState,
            navController = navController
        )
    }
}

@Composable
private fun Header(
    modifier: Modifier
) {
    val list = listOf(
        remember { Animatable(0f) },
        remember { Animatable(0f) },
        remember { Animatable(0f) },
    )

    val colors = listOf(Green, Lilac, Blue)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        Row(
            modifier = modifier
                .background(color = Orange)
                .fillMaxSize()
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            list.forEachIndexed { index, animatable ->
                LaunchedEffect(animatable) {
                    delay(index * 100L)
                    animatable.animateTo(
                        targetValue = 1f,
                        animationSpec = infiniteRepeatable(
                            animation = keyframes {
                                durationMillis = 2000
                                0.0f at 0 with LinearOutSlowInEasing
                                1.0f at 200 with LinearOutSlowInEasing
                                0.0f at 400 with LinearOutSlowInEasing
                                0.0f at 2000
                            },
                            repeatMode = RepeatMode.Restart,
                        )
                    )
                }

                Box(
                    modifier = modifier
                        .size(25.dp)
                        .graphicsLayer { translationY = -animatable.value * 15.dp.toPx() },
                ) {
                    Box(
                        modifier = modifier
                            .fillMaxSize()
                            .background(
                                color = colors[index],
                                shape = CircleShape
                            )
                    )
                }
                if (index != list.size - 1) {
                    Spacer(modifier = modifier.width(10.dp))
                }
            }
        }
    }
}

@Composable
private fun DrawerItems(
    modifier: Modifier,
    scaffoldState: ScaffoldState,
    navController: NavController,
) {
    val items = listOf(
        NavItems.LazyColumn,
        NavItems.LazyRow,
        NavItems.LazyGrid,
        NavItems.HorizontalPager
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.route ?: NavItems.Home

    val scope = rememberCoroutineScope()

    LazyColumn(content = {
        items(items = items, itemContent = { item ->
            DrawerItem(
                modifier = modifier,
                navItem = item,
                selected = currentRoute == item.route,
                onItemClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }
                }
            )
        })
    })
}

@Composable
private fun DrawerItem(
    modifier: Modifier,
    navItem: NavItems,
    selected: Boolean,
    onItemClick: (NavItems) -> Unit
) {
    val background =
        if (selected) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.primary

    val color = if (selected) Color.White else LightGray

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                onClick = { onItemClick(navItem) }
            )
            .padding(
                top = 10.dp
            )
            .clip(RoundedCornerShape(10.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .clickable(
                    onClick = { onItemClick(navItem) }
                )
                .background(background)
                .padding(10.dp)
                .clip(RoundedCornerShape(10.dp))
        ) {
            Image(
                painter = painterResource(id = navItem.icon),
                contentDescription = navItem.description,
                colorFilter = ColorFilter.tint(color)
            )

            Spacer(modifier = modifier.width(10.dp))

            Text(
                text = navItem.description,
                color = color,
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}