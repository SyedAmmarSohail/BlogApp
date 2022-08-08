package com.structure.blog_presentation.blog_overview

import app.cash.turbine.test
import com.structure.blog_domain.model.BlogModel
import com.structure.blog_domain.repository.BlogRepository
import com.structure.blog_domain.use_case.*
import com.structure.blog_presentation.factory.testBlogList
import com.structure.core.domain.model.BlogType
import com.structure.core.domain.preferences.Preferences
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import com.google.common.truth.Truth.assertThat

class BlogOverviewViewModelTest {

    private lateinit var preferences: Preferences
    private lateinit var trackerUseCases: TrackerUseCases
    private lateinit var viewModel: BlogOverviewViewModel

    @Mock
    private lateinit var repository: BlogRepository

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        MockitoAnnotations.openMocks(this)
        preferences = mockk(relaxed = true)
        trackerUseCases = TrackerUseCases(
            trackFood = TrackFood(repository),
            searchFood = SearchFood(repository),
            getFoodsForDate = GetFoodsForDate(repository),
            deleteTrackedFood = DeleteTrackedFood(repository),
            calculateMealNutrients = CalculateMealNutrients(preferences),
            getBlog = GetBlog(repository),
            searchBlog = SearchBlog(repository),
            storeBlogs = StoreBlogs(repository)
        )
    }

    @Test
    fun `get blogs success`(): Unit = runBlocking {
        whenever(trackerUseCases.getBlog()).thenReturn(Result.success(testBlogList))

        viewModel = BlogOverviewViewModel(
            trackerUseCases = trackerUseCases,
            preferences = preferences
        )

        assertThat(testBlogList).isEqualTo(viewModel.state.blogs)
        assertEquals(testBlogList, viewModel.state.blogs)
    }

    @Test
    fun `get blogs failure`(): Unit = runBlocking {
        whenever(trackerUseCases.getBlog()).thenReturn(Result.failure(Exception("Service Failed")))

        viewModel = BlogOverviewViewModel(
            trackerUseCases = trackerUseCases,
            preferences = preferences
        )
        assertThat(emptyList<BlogModel>()).isEqualTo(viewModel.state.blogs)
    }

    @Test
    fun `get blogs with Type`(): Unit = runBlocking {
        val flowTest = flow<List<BlogModel>> {
            emit(testBlogList)
        }

        whenever(trackerUseCases.searchBlog(BlogType.valueOf("FEATURED"))).thenReturn(flowTest)

        viewModel = BlogOverviewViewModel(
            trackerUseCases = trackerUseCases,
            preferences = preferences
        )

        viewModel.onEvent(BlogOverViewEvent.OnTabClick("FEATURED"))

        trackerUseCases.searchBlog(BlogType.valueOf("FEATURED")).test {

            assertThat(testBlogList).isEqualTo(awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }
}