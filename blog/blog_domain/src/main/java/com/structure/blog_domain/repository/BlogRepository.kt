package com.structure.blog_domain.repository

import com.structure.blog_domain.model.BlogModel
import com.structure.core.domain.model.BlogType
import kotlinx.coroutines.flow.Flow

interface BlogRepository {

    suspend fun getBlog(): Result<List<BlogModel>>

    suspend fun insertBlogs(blogList: List<BlogModel>)

    fun searchBlogsByType(blogType: BlogType): Flow<List<BlogModel>>

}