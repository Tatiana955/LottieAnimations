package com.example.animations.ui.screens.chameleon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.animations.R
import com.example.animations.ui.theme.*

@Composable
fun ChameleonScreen() {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.chameleon)
    )
    var isPlaying by remember { mutableStateOf(true) }
    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = isPlaying
    )

    val gradient = Brush.horizontalGradient(
        colors = listOf(
            Green,
            Lilac,
            Blue,
            Orange,
            Pink,
            Green
        )
    )

    LaunchedEffect(
        key1 = progress
    ) {
        if (progress == 0f) isPlaying = true
        if (progress == 1f) isPlaying = false
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            modifier = Modifier.size(400.dp),
            composition = composition,
            progress = { progress }
        )
        GradientButton(
            modifier = Modifier.width(320.dp),
            text = "Play Again",
            textColor = Color.White,
            gradient = gradient,
            onClick = { isPlaying = true }
        )
    }
}

@Composable
private fun GradientButton(
    modifier: Modifier,
    text: String,
    textColor: Color,
    gradient: Brush,
    onClick: () -> Unit
    ) {
    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent
        ),
        contentPadding = PaddingValues(),
        onClick = { onClick() }
    ) {
        Box(
            modifier = modifier
                .background(
                    brush = gradient
                )
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = textColor
            )
        }
    }
}
