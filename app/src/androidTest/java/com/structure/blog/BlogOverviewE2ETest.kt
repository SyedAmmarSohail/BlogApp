package com.structure.blog

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.google.common.truth.Truth.assertThat
import com.structure.blog.navigation.Route
import com.structure.blog.repository.BlogRepositoryFake
import com.structure.blog.ui.theme.BlogTheme
import com.structure.blog_domain.model.BlogModel
import com.structure.blog_domain.use_case.*
import com.structure.blog_presentation.blog_detail.BlogDetailScreen
import com.structure.blog_presentation.blog_overview.BlogOverviewScreen
import com.structure.blog_presentation.blog_overview.BlogOverviewViewModel
import com.structure.core.domain.model.BlogType
import com.structure.core.domain.preferences.Preferences
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@ExperimentalCoilApi
@ExperimentalComposeUiApi
@HiltAndroidTest
class BlogOverviewE2ETest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var blogRepositoryFake: BlogRepositoryFake
    private lateinit var preferences: Preferences
    private lateinit var blogOverviewViewModel: BlogOverviewViewModel
    private lateinit var blogUseCases: BlogUseCases
    private lateinit var navController: NavHostController


    @Before
    fun setup() {
        preferences = mockk(relaxed = true)
        blogRepositoryFake = BlogRepositoryFake()
        blogUseCases = BlogUseCases(
            getBlog = GetBlog(blogRepositoryFake),
            searchBlog = SearchBlog(blogRepositoryFake),
            storeBlogs = StoreBlogs(blogRepositoryFake)
        )
        blogOverviewViewModel = BlogOverviewViewModel(
            blogUseCases = blogUseCases,
            preferences = preferences
        )

        composeRule.setContent {
            BlogTheme {
                navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Route.BLOG_OVERVIEW
                    ) {

                        composable(Route.BLOG_OVERVIEW) {
                            BlogOverviewScreen(
                                onNavigateToDetail = { blog ->
                                    navController.navigate(
                                        Route.DETAIL + "/${blog.id}" +
                                                "/${blog.title}" +
                                                "/${blog.description}" +
                                                "/${blog.type.name}" +
                                                "/${
                                                    URLEncoder.encode(
                                                        blog.imageUrl,
                                                        StandardCharsets.UTF_8.toString()
                                                    )
                                                }" +
                                                "/${blog.link}" +
                                                "/${blog.date}"
                                    )
                                },
                                viewModel = blogOverviewViewModel,
                                onNavigateToProfile = {
                                    navController.navigate(Route.PROFILE)
                                }
                            )
                        }

                        composable(
                            route = Route.DETAIL + "/{blogId}/{blogTitle}/{blogDesc}/{blogType}/{blogImage}/{blogLink}/{blogDate}",
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
                                navArgument("blogImage") {
                                    type = NavType.StringType
                                },
                                navArgument("blogLink") {
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
                            val blogImage = it.arguments?.getString("blogImage")!!
                            val blogLink = it.arguments?.getString("blogLink")!!
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
                    }
                }

            }
        }
    }

    @Test
    fun `click_blog_item_to_navigate_detail_page`() {
        blogRepositoryFake.resultList = listOf(
            BlogModel(
                id = "id",
                title = "title",
                description = "description",
                imageUrl = "image-url",
                link = "link",
                type = BlogType.FEATURED,
                date = "date"
            )
        )

        composeRule.onNodeWithTag("searchField").performTextInput("title")

        composeRule.onRoot().printToLog("COMPOSE TREE")

        composeRule.onAllNodesWithContentDescription("cardItem").onFirst().performClick()

        assertThat(
            navController
                .currentDestination
                ?.route
                ?.startsWith(Route.DETAIL)
        ).isTrue()

        composeRule.onNodeWithContentDescription("backIcon").performClick()

        assertThat(
            navController
                .currentDestination
                ?.route
                ?.startsWith(Route.BLOG_OVERVIEW)
        )

    }
}