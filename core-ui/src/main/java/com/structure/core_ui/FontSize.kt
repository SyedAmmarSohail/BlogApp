package com.structure.core_ui

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

val sp1: TextUnit = 1.sp
val sp2: TextUnit = 2.sp
val sp3: TextUnit = 3.sp
val sp4: TextUnit = 4.sp
val sp5: TextUnit = 5.sp
val sp6: TextUnit = 6.sp
val sp7: TextUnit = 7.sp
val sp8: TextUnit = 8.sp
val sp9: TextUnit = 9.sp
val sp10: TextUnit = 10.sp
val sp11: TextUnit = 11.sp
val sp12: TextUnit = 12.sp
val sp13: TextUnit = 13.sp
val sp14: TextUnit = 14.sp
val sp15: TextUnit = 15.sp
val sp16: TextUnit = 16.sp
val sp17: TextUnit = 17.sp
val sp18: TextUnit = 18.sp
val sp19: TextUnit = 19.sp
val sp20: TextUnit = 20.sp
val sp21: TextUnit = 21.sp
val sp22: TextUnit = 22.sp
val sp23: TextUnit = 23.sp
val sp24: TextUnit = 24.sp
val sp25: TextUnit = 25.sp

data class FontSize(
    val view_1x: TextUnit = sp1,
    val view_2x: TextUnit = sp2,
    val view_3x: TextUnit = sp3,
    val view_4x: TextUnit = sp4,
    val view_5x: TextUnit = sp5,
    val view_6x: TextUnit = sp6,
    val view_7x: TextUnit = sp7,
    val view_8x: TextUnit = sp8,
    val view_9x: TextUnit = sp9,
    val view_10x: TextUnit = sp10,
    val view_11x: TextUnit = sp11,
    val view_12x: TextUnit = sp12,
    val view_13x: TextUnit = sp13,
    val view_14x: TextUnit = sp14,
    val view_15x: TextUnit = sp15,
    val view_16x: TextUnit = sp16,
    val view_17x: TextUnit = sp17,
    val view_18x: TextUnit = sp18,
    val view_19x: TextUnit = sp19,
    val view_20x: TextUnit = sp20,
    val view_21x: TextUnit = sp21,
    val view_22x: TextUnit = sp22,
    val view_23x: TextUnit = sp23,
    val view_24x: TextUnit = sp24,
    val view_25x: TextUnit = sp25
)

val LocalFontSize = compositionLocalOf { FontSize() }

val MaterialTheme.fontSize: FontSize
    @Composable
    @ReadOnlyComposable
    get() = LocalFontSize.current
