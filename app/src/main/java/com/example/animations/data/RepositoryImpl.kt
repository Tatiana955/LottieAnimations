package com.example.animations.data

import com.airbnb.lottie.compose.LottieCompositionSpec
import com.example.animations.domain.repository.Repository

class RepositoryImpl(
    private val local: LocalModel
): Repository {

    override fun getData(): List<LottieCompositionSpec> {
        return local.getData()
    }
}