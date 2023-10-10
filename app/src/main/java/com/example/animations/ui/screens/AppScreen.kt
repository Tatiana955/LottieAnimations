package com.example.animations.ui.screens

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.animations.ui.NavGraph
import com.example.animations.ui.components.AppBottomBar
import com.example.animations.ui.components.AppDrawer

@Composable
fun AppScreen() {
    val navController = rememberNavController()

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            AppBottomBar(
                navController = navController
            )
        },
        drawerContent = {
            AppDrawer(
                scaffoldState = scaffoldState,
                navController = navController
            )
        },
        drawerShape = RoundedCornerShape(10.dp),
        content = { paddingValues ->
            NavGraph(
                paddingValues = paddingValues,
                navController = navController
            )
        }
    )
}