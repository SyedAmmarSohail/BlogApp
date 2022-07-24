package com.structure.blog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.example.onboarding.OnboardingScreen
import com.example.onboarding.properties.OnboardingProperties
import com.structure.blog.ui.theme.BlogTheme
import com.structure.core.domain.preferences.Preferences
import com.structure.blog.navigation.Route
import com.structure.blog_presentation.blog_detail.BlogDetailScreen
import com.structure.blog_presentation.blog_overview.Blog
import com.structure.blog_presentation.blog_overview.BlogOverviewScreen
import com.structure.blog_presentation.blog_overview.blogList
import com.structure.onboarding_presentation.activity.ActivityScreen
import com.structure.onboarding_presentation.age.AgeScreen
import com.structure.onboarding_presentation.gender.GenderScreen
import com.structure.onboarding_presentation.goal.GoalScreen
import com.structure.onboarding_presentation.height.HeightScreen
import com.structure.onboarding_presentation.nutrient_goal.NutrientGoalScreen
import com.structure.onboarding_presentation.weight.WeightScreen
import com.structure.onboarding_presentation.welcome.WelcomeScreen
import com.structure.blog_presentation.search.SearchScreen
import com.structure.blog_presentation.tracker_overview.TrackerOverviewScreen
import com.structure.onboarding_presentation.onboarding.descriptionList
import com.structure.onboarding_presentation.onboarding.imageIdList
import com.structure.onboarding_presentation.onboarding.titleList
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalComposeUiApi
@ExperimentalCoilApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val shouldShowOnboarding = preferences.loadShouldShowOnboarding()
        setContent {
            BlogTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = if (shouldShowOnboarding) {
                            Route.ON_BOARDING
                        } else Route.TRACKER_OVERVIEW
                    ) {
                        composable(Route.WELCOME) {
                            WelcomeScreen(onNextClick = {
                                navController.navigate(Route.GENDER)
                            })
                        }
                        composable(Route.ON_BOARDING) {
                            OnboardingScreen(
                                imageIdList = imageIdList,
                                navController = navController,
                                lifecycleCoroutineScope = lifecycleScope,
                                titleList = titleList,
                                descriptionList = descriptionList,
                                skipTo = Route.BLOG_OVERVIEW,
                                properties = OnboardingProperties(
                                    buttonColor = Color.White,
                                    selectedDotColor = Color.White,
                                    imageContentScale = ContentScale.Crop,
                                    titleFontSize = 24.sp,
                                    descriptionFontSize = 16.sp,
                                    titleFontFamily = FontFamily.Default,
                                    descriptionFontFamily = FontFamily.Default,
                                    skipButtonName = "SKIP",
                                    nextButtonName = "NEXT"
                                )
                            )
                        }
                        composable(Route.AGE) {
                            AgeScreen(
                                scaffoldState = scaffoldState,
                                onNextClick = {
                                    navController.navigate(Route.HEIGHT)
                                }
                            )
                        }
                        composable(Route.GENDER) {
                            GenderScreen(onNextClick = {
                                navController.navigate(Route.AGE)
                            })
                        }
                        composable(Route.HEIGHT) {
                            HeightScreen(
                                scaffoldState = scaffoldState,
                                onNextClick = {
                                    navController.navigate(Route.WEIGHT)
                                }
                            )
                        }
                        composable(Route.WEIGHT) {
                            WeightScreen(
                                scaffoldState = scaffoldState,
                                onNextClick = {
                                    navController.navigate(Route.ACTIVITY)
                                }
                            )
                        }
                        composable(Route.NUTRIENT_GOAL) {
                            NutrientGoalScreen(
                                scaffoldState = scaffoldState,
                                onNextClick = {
                                    navController.navigate(Route.TRACKER_OVERVIEW)
                                }
                            )
                        }
                        composable(Route.ACTIVITY) {
                            ActivityScreen(onNextClick = {
                                navController.navigate(Route.GOAL)
                            })
                        }
                        composable(Route.GOAL) {
                            GoalScreen(onNextClick = {
                                navController.navigate(Route.NUTRIENT_GOAL)
                            })
                        }

                        composable(Route.TRACKER_OVERVIEW) {
                            BlogOverviewScreen(
                                onNavigateToDetail = { blog ->
                                    navController.navigate(
                                        Route.DETAIL + "/${blog.title}" +
                                                "/${blog.description}" +
                                                "/${blog.date}" +
                                                "/${blog.image}"
                                    )
                                }
                            )
//                            BlogDetailScreen(blogList[0])
//                        TrackerOverviewScreen(
//                                onNavigateToSearch = { mealName, day, month, year ->
//                                    navController.navigate(
//                                        Route.SEARCH + "/$mealName" +
//                                                "/$day" +
//                                                "/$month" +
//                                                "/$year"
//                                    )
//                                }
//                            )
                        }
                        composable(
                            route = Route.SEARCH + "/{mealName}/{dayOfMonth}/{month}/{year}",
                            arguments = listOf(
                                navArgument("mealName") {
                                    type = NavType.StringType
                                },
                                navArgument("dayOfMonth") {
                                    type = NavType.IntType
                                },
                                navArgument("month") {
                                    type = NavType.IntType
                                },
                                navArgument("year") {
                                    type = NavType.IntType
                                },
                            )
                        ) {
                            val mealName = it.arguments?.getString("mealName")!!
                            val dayOfMonth = it.arguments?.getInt("dayOfMonth")!!
                            val month = it.arguments?.getInt("month")!!
                            val year = it.arguments?.getInt("year")!!
                            SearchScreen(
                                scaffoldState = scaffoldState,
                                mealName = mealName,
                                dayOfMonth = dayOfMonth,
                                month = month,
                                year = year,
                                onNavigateUp = {
                                    navController.navigateUp()
                                }
                            )
                        }

                        composable(
                            route = Route.DETAIL + "/{blogTitle}/{blogDesc}/{blogDate}/{blogImage}",
                            arguments = listOf(
                                navArgument("blogTitle") {
                                    type = NavType.StringType
                                },
                                navArgument("blogDesc") {
                                    type = NavType.StringType
                                },
                                navArgument("blogDate") {
                                    type = NavType.StringType
                                },
                                navArgument("blogImage") {
                                    type = NavType.IntType
                                },
                            )
                        ) {
                            val blogTitle = it.arguments?.getString("blogTitle")!!
                            val blogDesc = it.arguments?.getString("blogDesc")!!
                            val blogDate = it.arguments?.getString("blogDate")!!
                            val blogImage = it.arguments?.getInt("blogImage")!!
                            BlogDetailScreen(Blog(blogTitle, blogDesc, blogDate, blogImage),
                                onNavigateUp = {
                                    navController.navigateUp()
                                }
                            )
                        }
                    }
                }

            }
        }
    }
}