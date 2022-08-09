package com.structure.blog.repository

import com.structure.blog_domain.model.BlogModel
import com.structure.blog_domain.repository.BlogRepository
import com.structure.core.domain.model.BlogType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import java.time.LocalDate
import kotlin.random.Random

class BlogRepositoryFake : BlogRepository {

    var shouldReturnError = false

    private val blogs = mutableListOf<BlogModel>()
    var resultList = listOf<BlogModel>(
        BlogModel(
            id = "id",
            title = "title",
            description = "description",
            imageUrl = "image-url",
            type = BlogType.FEATURED,
            date = "date"
        )
    )

    private val getBlogsFlow =
        MutableSharedFlow<List<BlogModel>>(replay = 1)

    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertTrackedFood(food: TrackedFood) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTrackedFood(food: TrackedFood) {
        TODO("Not yet implemented")
    }

    override fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        TODO("Not yet implemented")
    }

    override suspend fun getBlog(): Result<List<BlogModel>> {
        return if (shouldReturnError) {
            Result.failure(Throwable())
        } else {
            Result.success(resultList)
        }
    }

    override suspend fun insertBlogs(blogList: List<BlogModel>) {
        blogs.addAll(blogList.map {
            it.copy(id = Random.nextInt().toString())
        })
        getBlogsFlow.emit(blogs)
    }

    override fun searchBlogsByType(blogType: BlogType): Flow<List<BlogModel>> {
        return getBlogsFlow
    }
}