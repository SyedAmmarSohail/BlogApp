package com.structure.blog_data.repository

import com.structure.blog_data.local.TrackerDao
import com.structure.blog_data.mapper.*
import com.structure.blog_data.remote.OpenFoodApi
import com.structure.blog_data.remote.dto.BlogDto
import com.structure.blog_domain.model.BlogModel
import com.structure.blog_domain.model.TrackableFood
import com.structure.blog_domain.model.TrackedFood
import com.structure.blog_domain.repository.BlogRepository
import com.structure.core.domain.model.BlogType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class BlogRepositoryImpl(
    private val dao: TrackerDao,
    private val api: OpenFoodApi
) : BlogRepository {

    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        return try {
            val searchDto = api.searchFood(
                query = query,
                page = page,
                pageSize = pageSize
            )
            Result.success(
                searchDto.products
                    .filter {
                        val calculatedCalories =
                            it.nutriments.carbohydrates100g * 4f +
                                    it.nutriments.proteins100g * 4f +
                                    it.nutriments.fat100g * 9f
                        val lowerBound = calculatedCalories * 0.99f
                        val upperBound = calculatedCalories * 1.01f
                        it.nutriments.energyKcal100g in (lowerBound..upperBound)
                    }
                    .mapNotNull { it.toTrackableFood() }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun insertTrackedFood(food: TrackedFood) {
        dao.insertTrackedFood(food.toTrackedFoodEntity())
    }

    override suspend fun deleteTrackedFood(food: TrackedFood) {
        dao.deleteTrackedFood(food.toTrackedFoodEntity())
    }

    override fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        return dao.getFoodsForDate(
            day = localDate.dayOfMonth,
            month = localDate.monthValue,
            year = localDate.year
        ).map { entities ->
            entities.map { it.toTrackedFood() }
        }
    }

    override suspend fun getBlog(): Result<List<BlogModel>> {
        return try {
            val response = api.getBlogs()
            if (response.isSuccessful) {
                val blogDto = response.body() as BlogDto
                Result.success(
                    blogDto.blogs
                        .mapNotNull { it.toBlogModel() }
                )
            } else {
                Result.failure(Exception("Service Failed"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }



    override suspend fun insertBlogs(blogList: List<BlogModel>) {
        dao.insertBlogs(blogList.mapNotNull { it.toBlogEntity() })
    }

    override fun searchBlogsByType(blogType: BlogType): Flow<List<BlogModel>> {
        return dao.searchBlog(blogType.name
        ).map { entities ->
            entities.map { it.toBlogModel() }
        }
    }
}