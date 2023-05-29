package com.example.animations.domain.repository

import com.airbnb.lottie.compose.LottieCompositionSpec

interface Repository {

    fun getData(): List<LottieCompositionSpec>
}