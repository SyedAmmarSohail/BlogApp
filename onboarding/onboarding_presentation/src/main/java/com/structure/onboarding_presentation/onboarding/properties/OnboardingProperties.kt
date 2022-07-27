package com.structure.onboarding_presentation.onboarding.properties

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.structure.core_ui.DarkGray
import com.structure.core_ui.LightGray

data class OnboardingProperties (
    var buttonColor: Color = DarkGray,
    var selectedDotColor: Color = Color.DarkGray,
    var unselectedDotColor: Color = LightGray,
    var imageContentScale: ContentScale = ContentScale.Crop,
    var titleFontSize: TextUnit = 24.sp,
    var descriptionFontSize: TextUnit = 16.sp,
    var titleFontFamily: FontFamily = FontFamily.Default,
    var descriptionFontFamily: FontFamily = FontFamily.Default,
    var skipButtonName: String = "Skip",
    var nextButtonName: String = "Next",
    var nextArrowIconDrawableId: Int = 0,
    var titleColorList: List<Color> = listOf(
        Color.DarkGray,
        Color.DarkGray,
        Color.DarkGray,
        Color.DarkGray,
        Color.DarkGray
    ),
    var descriptionColorList: List<Color> = listOf(
        Color.DarkGray,
        Color.DarkGray,
        Color.DarkGray,
        Color.DarkGray,
        Color.DarkGray
    ),
    var backgroundColorStartList: List<Color> = listOf(
        Color.White,
        Color.White,
        Color.White,
        Color.White,
        Color.White,
    ),
    var backgroundColorEndList: List<Color> = listOf(
        Color.White,
        Color.White,
        Color.White,
        Color.White,
        Color.White,
    ),
)
