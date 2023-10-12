package com.example.animations.ui.screens.lazy_list.screens.row

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListLayoutInfo
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.animations.data.models.Item
import com.example.animations.ui.screens.lazy_list.LazyListViewModel
import com.example.animations.ui.screens.lazy_list.components.ActionButton
import com.example.animations.ui.screens.lazy_list.components.ItemBox
import com.example.animations.ui.theme.Blue
import com.example.animations.ui.theme.Green
import com.example.animations.ui.theme.Lilac
import com.example.animations.ui.theme.Orange
import com.example.animations.ui.theme.Pink
import com.example.animations.ui.theme.Yellow
import kotlin.math.abs
import kotlin.math.absoluteValue

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
                top = 30.dp,
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

        ItemListWithImage(
            modifier = modifier,
            list = list
        )

        ItemListWithIncreasedImage(
            modifier = modifier,
            list = list
        )

        ItemListWithChar(
            modifier = modifier
        )

        ItemListWithIcon(
            modifier = modifier
        )
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
            ItemBox(
                label = item.label,
                color = item.color,
                modifier = modifier.animateItemPlacement()
            )
        }
    }
}

@Composable
private fun ItemListWithImage(
    modifier: Modifier,
    list: SnapshotStateList<Item>
) {
    val state: LazyListState = rememberLazyListState()

    LazyRow(
        state = state,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp)
    ) {
        items(list, key = { it.id }) { item ->
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = modifier
                    .size(160.dp, 140.dp)
                    .graphicsLayer {
                        val value =
                            1 - (state.layoutInfo.normalizedItemPosition(item.id).absoluteValue * 0.15F)
                        alpha = value
                        scaleX = value
                        scaleY = value
                    }
            ) {
                Box(
                    modifier = modifier.background(Pink),
                ) {
                    Image(
                        painter = painterResource(id = item.imageUrl),
                        contentScale = ContentScale.Crop,
                        contentDescription = item.label,
                        modifier = modifier.requiredWidth(220.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun ItemListWithIncreasedImage(
    modifier: Modifier,
    list: SnapshotStateList<Item>
) {
    val state: LazyListState = rememberLazyListState()

    LazyRow(
        state = state,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp)
    ) {
        items(list, key = { it.id }) { item ->
            Box(
                modifier = modifier
                    .size(160.dp, 140.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Brush.verticalGradient(0f to Yellow, 1f to Orange))
                    .graphicsLayer {
                        val value =
                            1 - (state.layoutInfo.normalizedItemPosition(item.id).absoluteValue * 0.15F)
                        alpha = value
                        scaleX = value
                        scaleY = value
                    },
            ) {
                Image(
                    painter = painterResource(id = item.imageUrl),
                    contentScale = ContentScale.Crop,
                    contentDescription = item.label,
                    modifier = modifier.requiredWidth(220.dp)
                )

                Text(
                    text = item.label,
                    modifier = modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp, bottom = 8.dp, top = 32.dp),
                    color = Color.White,
                    maxLines = 1,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

// Returns the normalized center item offset (-1,1)
private fun LazyListLayoutInfo.normalizedItemPosition(key: Any): Float =
    visibleItemsInfo
        .firstOrNull { it.key == key }
        ?.let {
            val center = (viewportEndOffset + viewportStartOffset - it.size) / 2F
            (it.offset.toFloat() - center) / center
        }
        ?: 0F

@Composable
private fun ItemListWithChar(
    modifier: Modifier
) {
    val items = ('A'..'Z').map {
        Item(
            id = it.code,
            label = it.toString()
        )
    }
    val state = rememberLazyListState()

    BoxWithConstraints {
        val halfRowWidth = constraints.maxWidth / 2

        LazyRow(
            state = state,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            modifier = modifier
                .fillMaxWidth()
        ) {
            itemsIndexed(items) { i, item ->
                val opacity by remember {
                    derivedStateOf {
                        val currentItemInfo = state.layoutInfo.visibleItemsInfo
                            .firstOrNull { it.index == i }
                            ?: return@derivedStateOf 0.5f
                        val itemHalfSize = currentItemInfo.size / 2
                        val n = currentItemInfo.offset + itemHalfSize - halfRowWidth
                        (1f - minOf(1f, abs(n).toFloat() / halfRowWidth) * 0.5f)
                    }
                }

                ItemBox(
                    label = item.label,
                    color = Blue,
                    modifier = modifier
                        .scale(opacity)
                        .alpha(opacity)
                        .size(50.dp)
                )
            }
        }
    }
}

@Composable
private fun ItemListWithIcon(
    modifier: Modifier
) {
    val items = remember {
        listOf(
            Icons.Default.Person,
            Icons.Default.Favorite,
            Icons.Default.MailOutline,
            Icons.Default.Star,
            Icons.Default.Search,
        )
    }
    val state = rememberLazyListState(Int.MAX_VALUE / 2)
    val (rowHalfSize, setRowHalfSize) = remember { mutableStateOf<Int?>(null) }
    val horizontalContentPadding = 16.dp
    val density = LocalDensity.current

    LazyRow(
        state = state,
        contentPadding = PaddingValues(horizontal = horizontalContentPadding, vertical = 8.dp),
        modifier = modifier
            .fillMaxWidth()
            .onSizeChanged {
                setRowHalfSize(it.width / 2 - with(density) { horizontalContentPadding.roundToPx() })
            }
    ) {
        items(Int.MAX_VALUE) { i ->
            val index = i % items.size
            val opacity by remember(rowHalfSize) {
                derivedStateOf {
                    if (rowHalfSize == null) return@derivedStateOf 0.5f
                    val currentItemInfo = state.layoutInfo.visibleItemsInfo
                        .firstOrNull { it.index == i }
                        ?: return@derivedStateOf 0.5f
                    val itemHalfSize = currentItemInfo.size / 2
                    val n = currentItemInfo.offset + itemHalfSize - rowHalfSize
                    (1f - minOf(1f, abs(n).toFloat() / itemHalfSize) * 0.5f)
                }
            }

            Icon(
                items[index],
                contentDescription = null,
                modifier = modifier
                    .alpha(opacity)
                    .scale(opacity),
                tint = Lilac
            )
        }
    }
}