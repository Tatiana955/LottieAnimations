package com.example.animations.ui.screens.list

import androidx.lifecycle.ViewModel
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.example.animations.domain.repository.Repository

class ListViewModel(
    repository: Repository
): ViewModel() {

    val list: List<LottieCompositionSpec> = repository.getData()
}