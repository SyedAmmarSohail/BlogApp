package com.structure.onboarding_presentation.onboarding.state

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalFoundationApi::class)
data class OnBoardingState constructor(
    var imageId: Int = 0,
    var pagerState: PagerState? = null,
    var title: String? = null,
    var description: String? = null,
    var backgroundColorStart: Color = Color.White,
    var backgroundColorEnd: Color = Color.White,
    var titleColor: Color = Color.White,
    var descriptionColor: Color = Color.White,
)