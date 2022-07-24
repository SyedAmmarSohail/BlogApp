package com.example.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import com.example.onboarding.composables.DotsIndicator
import com.example.onboarding.composables.OnboardingButtons
import com.example.onboarding.composables.OnboardingScreenImage
import com.example.onboarding.composables.TitleAndContent
import com.example.onboarding.properties.OnboardingProperties
import com.structure.onboarding_presentation.onboarding.state.OnBoardingState
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @param navController: common navController between onboarding and the screen transitioned (e.g. home page),
 * @param lifecycleCoroutineScope: lifecycleScope - CoroutineScope tied to this LifecycleOwner's Lifecycle.
 * @param pageNum: Onboarding page number. It must be between 3 and 5 (both inclusive).
 * @param imageIdList: Image id list of the main images on the onboarding screen. You can set the ith item to 0 if you do not want image at the ith page.
 * @param imageIdList: Image id list of the main images on the onboarding pages.
 * @param titleList: Title texts of the onboarding pages.
 * @param descriptionList: Description texts on the onboarding pages.
 * @param skipTo: The route string that onboarding screen will end up with (in the end or with Skip Button click).
 * **/

@OptIn(ExperimentalPagerApi::class, kotlinx.coroutines.InternalCoroutinesApi::class)
@Composable
fun OnboardingScreen(
    navController: NavController,
    lifecycleCoroutineScope: LifecycleCoroutineScope,
    pageNum: Int = 3,
    imageIdList: List<Int> = listOf(0, 0, 0, 0, 0),
    titleList: List<String>,
    descriptionList: List<String>,
    skipTo: String,
    properties: OnboardingProperties,
) {
    val state = remember { OnBoardingState() }
    val pagerState = rememberPagerState()
    if (pageNum in 3..5) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            HorizontalPager(
                count = pageNum,
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
                        ), horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    OnboardingScreenImage(
                        imageId = state.imageId,
                        contentScale = properties.imageContentScale,
                    )
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(16.dp, 0.dp),
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

                        DotsIndicator(
                            totalDots = pageNum,
                            selectedIndex = pagerState.currentPage,
                            selectedColor = properties.selectedDotColor,
                            unSelectedColor = properties.unselectedDotColor
                        )
                        Column(
                            Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            OnboardingButtons(
                                navController = navController,
                                skipButtonName = properties.skipButtonName,
                                nextButtonName = properties.nextButtonName,
                                fontFamily = properties.titleFontFamily,
                                buttonColor = properties.buttonColor,
                                skipTo = skipTo,
                                nextOnClick = {
                                    lifecycleCoroutineScope.launch(Dispatchers.Main) {
                                        if (page == pageNum - 1) {
                                            navController.popBackStack()
                                            navController.navigate(skipTo)
                                            return@launch
                                        }
                                        pagerState.scrollToPage(page + 1)

                                    }
                                },
                                arrowDrawableId = properties.nextArrowIconDrawableId
                            )
                        }
                    }
                }
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


