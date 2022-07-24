package com.structure.blog_domain.repository

import com.structure.blog_domain.model.BlogModel
import com.structure.blog_domain.model.TrackableFood
import com.structure.blog_domain.model.TrackedFood
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface BlogRepository {

    suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>>

    suspend fun insertTrackedFood(food: TrackedFood)

    suspend fun deleteTrackedFood(food: TrackedFood)

    fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>>

    fun getBlog(): Flow<List<BlogModel>>
}