package com.example.animations.ui.screens.lazy_list.screens.grid

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.animations.data.models.Item
import com.example.animations.ui.screens.lazy_list.LazyListViewModel
import com.example.animations.ui.screens.lazy_list.components.ActionButton
import com.example.animations.ui.screens.lazy_list.components.ItemBox
import com.example.animations.ui.theme.LightGray

@Composable
fun LazyGridScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    viewModel: LazyListViewModel = viewModel()
) {
    Box(
        modifier = modifier
            .padding(paddingValues)
    ) {
        Content(
            modifier = modifier,
            viewModel = viewModel
        )
    }
}

@Composable
private fun Content(
    modifier: Modifier,
    viewModel: LazyListViewModel
) {
    val listItems = remember { viewModel.listItems }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = 10.dp,
                end = 10.dp,
                top = 50.dp,
                bottom = 10.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        ItemList(
            modifier = modifier,
            listItems = listItems
        )

        ActionButton(
            modifier = modifier,
            text = "Shuffle",
            color = LightGray,
            onClick = { viewModel.shuffleList() }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ItemList(
    modifier: Modifier,
    listItems: SnapshotStateList<Item>
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ItemBox(
            modifier = modifier
                .padding(bottom = 20.dp),
            label = "Lazy Vertical Grid",
            color = LightGray
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp),
            horizontalArrangement = Arrangement.spacedBy(space = 8.dp)
        ) {
            items(
                items = listItems,
                key = { it.id }
            ) { item ->
                ItemBox(
                    label = item.label,
                    color = item.color,
                    modifier = modifier
                        .animateItemPlacement()
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .height(48.dp),
                )
            }
        }

        ItemBox(
            modifier = modifier
                .padding(bottom = 20.dp),
            label = "Lazy Horizontal Grid",
            color = LightGray
        )

        LazyHorizontalGrid(
            rows = GridCells.Fixed(3),
            modifier = modifier
                .height(300.dp)
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(space = 8.dp)
        ) {
            items(items = listItems) { item ->
                ItemBox(
                    label = item.label,
                    color = item.color,
                    modifier = modifier
                        .animateItemPlacement(
                            animationSpec = tween(
                                durationMillis = 500,
                                easing = LinearOutSlowInEasing,
                            )
                        )
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
            }
        }
    }
}