package com.structure.onboarding_presentation.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import com.structure.onboarding_presentation.onboarding.composables.DotsIndicator
import com.structure.onboarding_presentation.onboarding.composables.OnBoardingButtons
import com.structure.onboarding_presentation.onboarding.composables.OnBoardingScreenImage
import com.structure.onboarding_presentation.onboarding.composables.TitleAndContent
import com.structure.onboarding_presentation.onboarding.properties.OnBoardingProperties
import com.structure.onboarding_presentation.onboarding.state.OnBoardingState
import com.structure.core_ui.spacing
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    navController: NavController,
    lifecycleCoroutineScope: LifecycleCoroutineScope,
    pageNum: Int = 3,
    imageIdList: List<Int> = listOf(0, 0, 0, 0, 0),
    titleList: List<String>,
    descriptionList: List<String>,
    skipTo: String,
    properties: OnBoardingProperties,
) {
    val state = remember { OnBoardingState() }
    val pagerState = rememberPagerState(pageCount = { pageNum })
    val scope = rememberCoroutineScope()
    if (pageNum in 3..5) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxHeight(),
            ) { page ->
                when (page) {
                    0 -> setPageValues(
                        0,
                        state,
                        imageIdList,
                        titleList,
                        descriptionList,
                        properties.backgroundColorStartList,
                        properties.backgroundColorEndList,
                        properties.titleColorList,
                        properties.descriptionColorList
                    )
                    1 -> {
                        setPageValues(
                            1,
                            state,
                            imageIdList,
                            titleList,
                            descriptionList,
                            properties.backgroundColorStartList,
                            properties.backgroundColorEndList,
                            properties.titleColorList,
                            properties.descriptionColorList
                        )
                    }
                    2 -> {
                        setPageValues(
                            2,
                            state,
                            imageIdList,
                            titleList,
                            descriptionList,
                            properties.backgroundColorStartList,
                            properties.backgroundColorEndList,
                            properties.titleColorList,
                            properties.descriptionColorList
                        )
                    }
                    3 -> {
                        setPageValues(
                            3,
                            state,
                            imageIdList,
                            titleList,
                            descriptionList,
                            properties.backgroundColorStartList,
                            properties.backgroundColorEndList,
                            properties.titleColorList,
                            properties.descriptionColorList
                        )
                    }
                    4 -> {
                        setPageValues(
                            4,
                            state,
                            imageIdList,
                            titleList,
                            descriptionList,
                            properties.backgroundColorStartList,
                            properties.backgroundColorEndList,
                            properties.titleColorList,
                            properties.descriptionColorList
                        )
                    }
                }
                Column(
                    Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    state.backgroundColorStart,
                                    state.backgroundColorEnd
                                )
                            )
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    OnBoardingScreenImage(
                        imageId = state.imageId,
                        contentScale = properties.imageContentScale,
                    )
                    Column(
                        Modifier
                            .padding(horizontal = MaterialTheme.spacing.view_4x),
                    ) {

                        state.title?.let {
                            state.description?.let { it1 ->
                                TitleAndContent(
                                    title = it,
                                    description = it1,
                                    titleColor = state.titleColor,
                                    descriptionColor = state.descriptionColor,
                                    titleFontSize = properties.titleFontSize,
                                    descriptionFontSize = properties.descriptionFontSize,
                                    titleFontFamily = properties.titleFontFamily,
                                    descriptionFontFamily = properties.descriptionFontFamily
                                )
                            }
                        }
                    }
                }
            }
            Column(
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                DotsIndicator(
                    totalDots = pageNum,
                    selectedIndex = pagerState.currentPage,
                    selectedColor = properties.selectedDotColor,
                    unSelectedColor = properties.unselectedDotColor
                )
                OnBoardingButtons(
                    navController = navController,
                    skipButtonName = properties.skipButtonName,
                    nextButtonName = properties.nextButtonName,
                    fontFamily = properties.titleFontFamily,
                    buttonColor = properties.buttonColor,
                    skipTo = skipTo,
                    nextOnClick = {
                        scope.launch {
                            if (pagerState.currentPage == pageNum - 1) {
                                navController.popBackStack()
                                navController.navigate(skipTo)
                                return@launch
                            }
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    },
                    arrowDrawableId = properties.nextArrowIconDrawableId
                )
            }
        }
    }
}

fun setPageValues(
    currentPageIndex: Int,
    state: OnBoardingState,
    imageIdList: List<Int>,
    titleList: List<String>,
    descriptionList: List<String>,
    backgroundColorStartList: List<Color>,
    backgroundColorEndList: List<Color>,
    titleColorList: List<Color>,
    descriptionColorList: List<Color>
) {
    state.imageId = imageIdList[currentPageIndex]
    state.title = titleList[currentPageIndex]
    state.description = descriptionList[currentPageIndex]
    state.backgroundColorStart = backgroundColorStartList[currentPageIndex]
    state.backgroundColorEnd = backgroundColorEndList[currentPageIndex]
    state.titleColor = titleColorList[currentPageIndex]
    state.descriptionColor = descriptionColorList[currentPageIndex]
}


