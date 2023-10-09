package com.example.animations.ui.screens.lazy_list.screens.row

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.animations.data.models.Item
import com.example.animations.ui.screens.lazy_list.LazyListViewModel
import com.example.animations.ui.screens.lazy_list.components.ActionButton
import com.example.animations.ui.theme.Green
import com.example.animations.ui.theme.Lilac
import com.example.animations.ui.theme.Pink

@Composable
fun LazyRowScreen(
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
    val list = remember { viewModel.listItems }

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
        verticalArrangement = Arrangement.SpaceAround
    ) {

        ItemList(
            modifier = modifier,
            list = list
        )

        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {

            ActionButton(
                modifier = modifier.weight(1f),
                text = "Shuffle",
                color = Pink,
                onClick = { viewModel.shuffleList() }
            )

            ActionButton(
                modifier = modifier.weight(1f),
                text = "Add",
                color = Green,
                onClick = { viewModel.addItem() }
            )

            ActionButton(
                modifier = modifier.weight(1f),
                text = "Remove",
                enabled = list.isNotEmpty(),
                color = Lilac,
                onClick = { viewModel.removeItem() }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ItemList(
    modifier: Modifier,
    list: SnapshotStateList<Item>
) {
    LazyRow(
        contentPadding = PaddingValues(all = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(
            items = list,
            key = { it.id }
        ) { item ->
            Item(
                label = item.label,
                color = item.color,
                modifier = modifier.animateItemPlacement()
            )
        }
    }
}

@Composable
private fun Item(
    modifier: Modifier = Modifier,
    label: String,
    color: Color
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(color = color)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            modifier = Modifier.padding(all = 8.dp),
            text = label,
            style = MaterialTheme.typography.body1,
            color = Color.White
        )
    }
}