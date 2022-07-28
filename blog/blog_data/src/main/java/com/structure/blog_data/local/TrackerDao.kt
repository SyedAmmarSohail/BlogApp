package com.structure.blog_data.local

import androidx.room.*
import com.structure.blog_data.local.entity.BlogEntity
import com.structure.blog_data.local.entity.TrackedFoodEntity
import com.structure.core.domain.model.BlogType
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrackedFood(trackedFoodEntity: TrackedFoodEntity)

    @Delete
    suspend fun deleteTrackedFood(trackedFoodEntity: TrackedFoodEntity)

    @Query(
        """
            SELECT *
            FROM trackedfoodentity
            WHERE dayOfMonth = :day AND month = :month AND year = :year
        """
    )
    fun getFoodsForDate(day: Int, month: Int, year: Int): Flow<List<TrackedFoodEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBlogs(blogList: List<BlogEntity>)

    @Query("SELECT * FROM blogentity WHERE type = :blogType")
    fun searchBlog(blogType: String): Flow<List<BlogEntity>>

}