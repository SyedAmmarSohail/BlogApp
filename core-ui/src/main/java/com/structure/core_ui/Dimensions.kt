package com.structure.core_ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val view_0: Dp = 0.dp,
    val view_1: Dp = 1.dp,
    val view_1x: Dp = 4.dp,
    val view_2x: Dp = 8.dp,
    val view_3x: Dp = 12.dp,
    val view_4x: Dp = 16.dp,
    val view_5x: Dp = 20.dp,
    val view_6x: Dp = 24.dp,
    val view_7x: Dp = 28.dp,
    val view_8x: Dp = 32.dp,
    val view_9x: Dp = 36.dp,
    val view_10x: Dp = 40.dp,
    val view_11x: Dp = 44.dp,
    val view_12x: Dp = 48.dp,
    val view_13x: Dp = 52.dp,
    val view_14x: Dp = 56.dp,
    val view_15x: Dp = 60.dp,
    val view_16x: Dp = 64.dp,
    val view_17x: Dp = 68.dp,
    val view_18x: Dp = 72.dp,
    val view_19x: Dp = 76.dp,
    val view_20x: Dp = 80.dp,
    val view_21x: Dp = 84.dp,
    val view_22x: Dp = 88.dp,
    val view_23x: Dp = 92.dp,
    val view_24x: Dp = 96.dp,
    val view_25x: Dp = 100.dp,
    val view_30x: Dp = 120.dp,
    val view_50x: Dp = 120.dp
)

val LocalSpacing = compositionLocalOf { Dimensions() }

val MaterialTheme.spacing: Dimensions
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current

@Composable
fun spacerHeight(height : Dp) = Spacer(modifier = Modifier.height(height))

@Composable
fun spacerWidth(width : Dp) = Spacer(modifier = Modifier.width(width))

