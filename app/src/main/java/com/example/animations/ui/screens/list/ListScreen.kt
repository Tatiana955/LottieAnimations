package com.example.animations.ui.screens.list

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.animations.data.LocalModel
import com.example.animations.data.RepositoryImpl
import com.smarttoolfactory.animatedlist.AnimatedInfiniteLazyRow

@Composable
fun ListScreen(
) {
    val local = LocalModel()
    val repository = RepositoryImpl(local)
    val viewModel: ListViewModel = viewModel(
        factory = ListViewModelFactory(repository)
    )
    val list = viewModel.list

    var visibleIteCount by remember { mutableStateOf(3f) }
    var selectorIndex by remember { mutableStateOf(1f) }
    val itemScaleRange by remember { mutableStateOf(1f) }
    var inactiveItemFraction by remember { mutableStateOf(70f) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        ) {
            AnimatedInfiniteLazyRow(
                items = list,
                visibleItemCount = visibleIteCount.toInt(),
                selectorIndex = selectorIndex.toInt(),
                itemScaleRange = itemScaleRange.toInt(),
                inactiveItemPercent = inactiveItemFraction.toInt(),
                itemContent = { animationProgress, _, item, width ->
                    val scale = animationProgress.scale
                    val composition by rememberLottieComposition(item)
                    LottieAnimation(
                        modifier = Modifier
                            .size(width)
                            .scale(scale),
                        composition = composition
                    )
                }
            )

            val color = SliderDefaults.colors(
                thumbColor = MaterialTheme.colors.secondary,
                activeTrackColor = MaterialTheme.colors.secondary
            )

            TextItem(
                text = "Inactive Item Percent: ${inactiveItemFraction.toInt()}"
            )
            Slider(
                value = inactiveItemFraction,
                onValueChange = {
                    inactiveItemFraction = it
                },
                valueRange = 0f..100f,
                colors = color
            )

            TextItem(
                text = "Selector Index: ${selectorIndex.toInt()}"
            )
            Slider(
                value = selectorIndex,
                onValueChange = {
                    selectorIndex = it
                },
                steps = (visibleIteCount - 2).toInt(),
                valueRange = 0f..(visibleIteCount - 1),
                colors = color
            )

            TextItem(
                "Visible Item Count: ${visibleIteCount.toInt()}"
            )
            Slider(
                value = visibleIteCount,
                onValueChange = {
                    visibleIteCount = it
                },
                steps = 7,
                valueRange = 3f..11f,
                colors = color
            )
        }
    }
}

@Composable
private fun TextItem(
    text: String
) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = text,
        color = MaterialTheme.colors.secondary,
        fontSize = 16.sp,
        textAlign = TextAlign.Center
    )
}