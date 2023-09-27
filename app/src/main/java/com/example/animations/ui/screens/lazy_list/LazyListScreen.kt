package com.example.animations.ui.screens.lazy_list

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.animations.data.models.Item
import com.example.animations.ui.theme.LightGray
import com.example.animations.ui.theme.Yellow

@Composable
fun LazyListScreen(
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
        label = "animated color buttons"
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 10.dp,
                end = 10.dp,
                top = 50.dp,
                bottom = 10.dp
            ),
    ) {
        ItemList(
            modifier = modifier.weight(1f),
            listItems = listItems
        )

        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            ActionButton(
                modifier = modifier.weight(1f),
                text = "Shuffle",
                color = color,
                onClick = { viewModel.shuffleList() }
            )

            ActionButton(
                modifier = modifier.weight(1f),
                text = "Add",
                color = color,
                onClick = { viewModel.addItem() }
            )

            ActionButton(
                modifier = modifier.weight(1f),
                text = "Remove",
                enabled = listItems.isNotEmpty(),
                color = color,
                onClick = { viewModel.removeItem() }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ItemList(
    modifier: Modifier,
    listItems: SnapshotStateList<Item>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
    ) {
        items(items = listItems) { item ->
            Item(
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
                    .height(48.dp),
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
            .background(color = color),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.body1,
            color = Color.White
        )
    }
}

@Composable
private fun ActionButton(
    modifier: Modifier,
    text: String,
    enabled: Boolean = true,
    color: Color,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = modifier
            .padding(horizontal = 8.dp),
        enabled = enabled,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = color,
            contentColor = Color.White
        )
    ) {
        Text(text = text)
    }
}