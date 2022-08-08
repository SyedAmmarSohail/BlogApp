package com.structure.blog_data.repository

import com.google.common.truth.Truth.assertThat
import com.structure.blog_data.remote.OpenFoodApi
import com.structure.blog_data.remote.dto.Article
import com.structure.blog_data.remote.dto.BlogDto
import com.structure.core.domain.model.BlogType
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.whenever
import retrofit2.Response
import java.io.IOException

class BlogRepositoryImplTest {

    private lateinit var repository: BlogRepositoryImpl

    @Mock
    private lateinit var service: OpenFoodApi

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = BlogRepositoryImpl(
            dao = mockk(relaxed = true),
            api = service
        )
    }

    @Test
    fun `get blogs, return success`(): Unit = runBlocking {
        whenever(service.getBlogs()).thenReturn(
            Response.success(
                getBlogDto()
            )
        )

        val result = repository.getBlog()

        assertThat(result.isSuccess).isTrue()
    }

    @Test
    fun `get blogs, return error`(): Unit = runBlocking {
        whenever(service.getBlogs()).thenReturn(Response.error(400, responseBody()))

        val result = repository.getBlog()

        assertThat(result.isFailure).isTrue()
    }

    @Test
    fun `get blogs, return exception`(): Unit = runBlocking {
        doAnswer { throw IOException() }.`when`(service).getBlogs()

        val result = repository.getBlog()

        assertThat(result.isFailure).isTrue()
    }

    private fun responseBody() = ResponseBody.create("application/json".toMediaTypeOrNull(), "")

    private fun getBlogDto() = BlogDto(
        blogs = listOf(
            Article(
                "id",
                title = "title",
                description = "description",
                "imageUrl",
                type = BlogType.FEATURED,
                date = "date"
            )
        )
    )
}