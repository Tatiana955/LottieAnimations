package com.example.animations.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.animations.domain.util.NavItems
import com.example.animations.ui.screens.chameleon.ChameleonScreen
import com.example.animations.ui.screens.lazy_list.screens.column.LazyColumnScreen
import com.example.animations.ui.screens.lazy_list.screens.grid.LazyGridScreen
import com.example.animations.ui.screens.lazy_list.screens.row.LazyRowScreen
import com.example.animations.ui.screens.list.ListScreen
import com.example.animations.ui.screens.start.StartScreen

@Composable
fun NavGraph(
    paddingValues: PaddingValues,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavItems.Home.route
    ) {
        composable(NavItems.Home.route) {
            StartScreen()
        }
        composable(NavItems.List.route) {
            ListScreen()
        }
        composable(NavItems.Chameleon.route) {
            ChameleonScreen()
        }

        composable(NavItems.LazyColumn.route) {
            LazyColumnScreen(
                paddingValues = paddingValues
            )
        }
        composable(NavItems.LazyRow.route) {
            LazyRowScreen(
                paddingValues = paddingValues
            )
        }
        composable(NavItems.LazyGrid.route) {
            LazyGridScreen(
                paddingValues = paddingValues
            )
        }
    }
}