package com.example.animations.ui.screens.start

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.*
import com.example.animations.R

@Composable
fun StartScreen() {
    val composition = rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(
            resId = R.raw.background
        )
    )
    val progress by animateLottieCompositionAsState(
        composition = composition.value,
        iterations = LottieConstants.IterateForever
    )
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        LottieAnimation(
            modifier = Modifier.fillMaxSize(),
            composition = composition.value,
            progress = { progress }
        )
    }
}