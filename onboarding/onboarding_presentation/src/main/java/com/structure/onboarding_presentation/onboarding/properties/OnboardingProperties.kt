package com.example.onboarding.properties

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

/**
 * @param buttonColor: Color of Skip and Next Buttons. Default: DarkGray
 * @param selectedDotColor: Color of dot of the selected item. Default: DarkGray
 * @param unselectedDotColor: Color of dot of the unselected item. Default: Gray
 * @param imageContentScale: Content scale param of main image on the onboarding pages. Default: ContentScale.Crop
 * @param titleFontSize: Font size of title on the onboarding pages. Default: 24sp
 * @param descriptionFontSize: Font size of description on the onboarding pages. Default: 16sp
 * @param titleFontFamily: Font family of title on the onboarding pages. Default: FontFamily.Default
 * @param descriptionFontFamily: Font family of description on the onboarding pages. Default: FontFamily.Default
 * @param skipButtonName: Text of the Skip button (at left). You can localize text of the Skip Button. Default: SKIP
 * @param nextButtonName: Text of the Next button (at right). You can localize text of the Next Button. Default: NEXT
 * @param nextArrowIconDrawableId: Icon item next to the Next button. You can add an icon to Next button, like arrow. Default: 0
 * @param titleColorList: List of title color on each page. Default: DarkGray (all)
 * @param descriptionColorList: List of description color on each page. Default: DarkGray (all)
 * @param backgroundColorStartList: Start color for a gradient background for each page. Default: White (all)
 * @param backgroundColorEndList: End color for a gradient background for each page. Default: White (all)
 * **/
data class OnboardingProperties (
    var buttonColor: Color = Color.DarkGray,
    var selectedDotColor: Color = Color.DarkGray,
    var unselectedDotColor: Color = Color.Gray,
    var imageContentScale: ContentScale = ContentScale.Crop,
    var titleFontSize: TextUnit = 24.sp,
    var descriptionFontSize: TextUnit = 16.sp,
    var titleFontFamily: FontFamily = FontFamily.Default,
    var descriptionFontFamily: FontFamily = FontFamily.Default,
    var skipButtonName: String = "SKIP",
    var nextButtonName: String = "NEXT",
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
