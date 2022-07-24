package com.structure.onboarding_presentation.onboarding.state

import androidx.compose.ui.graphics.Color
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

data class OnBoardingState @OptIn(ExperimentalPagerApi::class) constructor(
    var imageId: Int = 0,
    var pagerState: PagerState? = null,
    var title: String? = null,
    var description: String? = null,
    var backgroundColorStart: Color = Color.White,
    var backgroundColorEnd: Color = Color.White,
    var titleColor: Color = Color.White,
    var descriptionColor: Color = Color.White,
)