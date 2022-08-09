package com.structure.blog_data.local

import androidx.room.*
import com.structure.blog_data.local.entity.BlogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BlogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBlogs(blogList: List<BlogEntity>)

    @Query("SELECT * FROM blogentity WHERE type = :blogType")
    fun searchBlog(blogType: String): Flow<List<BlogEntity>>

}