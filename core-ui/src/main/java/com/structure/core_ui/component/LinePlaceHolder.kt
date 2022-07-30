package com.structure.core_ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LinePlaceHolder(
    width: Dp = 100.dp
) = ShimmerAnimation {
    Spacer(
        modifier = Modifier
            .width(width)
            .height(16.dp)
            .background(
                brush = it,
            )
    )
}