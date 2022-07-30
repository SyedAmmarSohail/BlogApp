package com.structure.core_ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun ImagePlaceHolder(
    size: Dp = 100.dp
) = ShimmerAnimation {
    Spacer(
        modifier = Modifier
            .size(size)
            .background(
                brush = it,
            )
    )
}