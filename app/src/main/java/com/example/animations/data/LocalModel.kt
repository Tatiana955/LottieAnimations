package com.example.animations.data

import com.airbnb.lottie.compose.LottieCompositionSpec
import com.example.animations.R

class LocalModel {

    fun getData(): List<LottieCompositionSpec> {
        return listOf(
            LottieCompositionSpec.RawRes(R.raw.chameleon),
            LottieCompositionSpec.RawRes(R.raw.eve),
            LottieCompositionSpec.RawRes(R.raw.cat_tv_animation),
            LottieCompositionSpec.RawRes(R.raw.dinosaur_running),
            LottieCompositionSpec.RawRes(R.raw.in_tempus),
            LottieCompositionSpec.RawRes(R.raw.little_cute_octopus),
            LottieCompositionSpec.RawRes(R.raw.moody_giraffe)
        )
    }
}