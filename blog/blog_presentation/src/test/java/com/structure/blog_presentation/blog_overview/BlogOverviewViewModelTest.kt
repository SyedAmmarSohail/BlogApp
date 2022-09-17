package com.structure.blog_presentation.blog_overview

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.structure.blog_domain.model.BlogModel
import com.structure.blog_domain.repository.BlogRepository
import com.structure.blog_domain.use_case.*
import com.structure.blog_presentation.factory.testBlogList
import com.structure.core.domain.model.BlogType
import com.structure.core.domain.preferences.Preferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Rule

@ExperimentalCoroutinesApi
class BlogOverviewViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var blogUseCases: BlogUseCases
    private lateinit var viewModel: BlogOverviewViewModel

    @MockK
    private lateinit var preferences: Preferences

    @MockK
    private lateinit var repository: BlogRepository

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        MockKAnnotations.init(this, relaxed = true)
        blogUseCases = BlogUseCases(
            getBlog = GetBlog(repository),
            searchBlog = SearchBlog(repository),
            storeBlogs = StoreBlogs(repository)
        )
    }

    @After
    fun tearDown(){
        unmockkAll()
        Dispatchers.resetMain()
    }

    @Test
    fun `get blogs success`() = runTest {

        coEvery { blogUseCases.getBlog() } returns Result.success(testBlogList)
        viewModel = BlogOverviewViewModel(
            blogUseCases = blogUseCases,
            preferences = preferences
        )
//        advanceUntilIdle()
        advanceTimeBy(2000)
        assertThat(true).isEqualTo(viewModel.state.isSearching)

        coVerify { blogUseCases.getBlog() }
        if (!viewModel.state.isSearching) {
            assertThat(testBlogList).isEqualTo(viewModel.state.blogs)
        }
    }

    @Test
    fun `get blogs failure`(): Unit = runBlocking {
        coEvery { blogUseCases.getBlog() } returns Result.failure(Exception("Service Failed"))

        viewModel = BlogOverviewViewModel(
            blogUseCases = blogUseCases,
            preferences = preferences
        )
        assertThat(emptyList<BlogModel>()).isEqualTo(viewModel.state.blogs)
    }

    @Test
    fun `get blogs with Type`(): Unit = runBlocking {
        val flowTest = flow<List<BlogModel>> {
            emit(testBlogList)
        }

        coEvery { blogUseCases.searchBlog(BlogType.valueOf("FEATURED")) } returns flowTest

        viewModel = BlogOverviewViewModel(
            blogUseCases = blogUseCases,
            preferences = preferences
        )

        viewModel.onEvent(BlogOverViewEvent.OnTabClick("FEATURED"))

        blogUseCases.searchBlog(BlogType.valueOf("FEATURED")).test {

            assertThat(testBlogList).isEqualTo(awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }
}