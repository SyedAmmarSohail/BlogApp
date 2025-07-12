package com.structure.blog

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.structure.blog.ui.theme.BlogTheme
import com.structure.core.domain.preferences.Preferences
import com.structure.blog.navigation.Route
import com.structure.blog_domain.model.BlogModel
import com.structure.blog_presentation.blog_detail.BlogDetailScreen
import com.structure.blog_presentation.blog_overview.BlogOverviewScreen
import com.structure.core.domain.model.BlogType
import com.structure.core_ui.DarkGray
import com.structure.core_ui.Purple
import com.structure.onboarding_presentation.onboarding.OnBoardingScreen
import com.structure.onboarding_presentation.onboarding.descriptionList
import com.structure.onboarding_presentation.onboarding.imageIdList
import com.structure.onboarding_presentation.onboarding.properties.OnBoardingProperties
import com.structure.onboarding_presentation.onboarding.titleList
import com.structure.profile_presentation.profile.ProfileScreen
import dagger.hilt.android.AndroidEntryPoint
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.time.temporal.TemporalAdjusters.next
import javax.inject.Inject

@ExperimentalComposeUiApi
@ExperimentalCoilApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferences: Preferences

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
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
                        } else Route.BLOG_OVERVIEW
                    ) {
                        composable(Route.ON_BOARDING) {
                            OnBoardingScreen(
                                imageIdList = imageIdList,
                                navController = navController,
                                lifecycleCoroutineScope = lifecycleScope,
                                titleList = titleList,
                                descriptionList = descriptionList,
                                skipTo = Route.BLOG_OVERVIEW,
                                properties = OnBoardingProperties(
                                    buttonColor = DarkGray,
                                    selectedDotColor = Purple,
                                    imageContentScale = ContentScale.Crop,
                                    titleFontSize = 24.sp,
                                    descriptionFontSize = 16.sp,
                                    titleFontFamily = FontFamily.Default,
                                    descriptionFontFamily = FontFamily.Default,
                                    skipButtonName = stringResource(id = com.structure.core.R.string.skip),
                                    nextButtonName = stringResource(id = com.structure.core.R.string.next)
                                )
                            )
                        }
                        composable(Route.BLOG_OVERVIEW) {
                            BlogOverviewScreen(
                                onNavigateToDetail = { blog ->
                                    val encodedUrl = URLEncoder.encode(blog.link, StandardCharsets.UTF_8.toString())

                                    navController.navigate(
                                        Route.DETAIL + "/${blog.id}" +
                                                "/${blog.title}" +
                                                "/${blog.description}" +
                                                "/${blog.type.name}" +
                                                "/${encodedUrl}" +
                                                "/${
                                                    URLEncoder.encode(
                                                        blog.imageUrl,
                                                        StandardCharsets.UTF_8.toString()
                                                    )
                                                }" +
                                                "/${blog.date}"
                                    )
                                },
                                onNavigateToProfile = {
                                    navController.navigate(Route.PROFILE)
                                }
                            )
                        }
                        composable(
                            route = Route.DETAIL + "/{blogId}/{blogTitle}/{blogDesc}/{blogType}/{blogLink}/{blogImage}/{blogDate}",
                            arguments = listOf(
                                navArgument("blogId") {
                                    type = NavType.StringType
                                },
                                navArgument("blogTitle") {
                                    type = NavType.StringType
                                },
                                navArgument("blogDesc") {
                                    type = NavType.StringType
                                },
                                navArgument("blogType") {
                                    type = NavType.StringType
                                },
                                navArgument("blogLink") {
                                    type = NavType.StringType
                                },
                                navArgument("blogImage") {
                                    type = NavType.StringType
                                },
                                navArgument("blogDate") {
                                    type = NavType.StringType
                                },
                            )
                        ) {
                            val blogId = it.arguments?.getString("blogId")!!
                            val blogTitle = it.arguments?.getString("blogTitle")!!
                            val blogDesc = it.arguments?.getString("blogDesc")!!
                            val blogType = it.arguments?.getString("blogType")!!
                            val blogLink = it.arguments?.getString("blogLink")!!
                            val blogImage = it.arguments?.getString("blogImage")!!
                            val blogDate = it.arguments?.getString("blogDate")!!
                            BlogDetailScreen(
                                BlogModel(
                                    id = blogId,
                                    title = blogTitle,
                                    description = blogDesc,
                                    imageUrl = blogImage,
                                    link = blogLink,
                                    type = BlogType.valueOf(blogType),
                                    date = blogDate,
                                ),
                                onNavigateUp = {
                                    navController.navigateUp()
                                }
                            )
                        }

                        composable(Route.PROFILE) {
                            ProfileScreen(
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