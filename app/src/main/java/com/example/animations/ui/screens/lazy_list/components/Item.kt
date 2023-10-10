package com.example.animations.ui.screens.lazy_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Item(
    modifier: Modifier,
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