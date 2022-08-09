package com.structure.blog_data.repository

import com.structure.blog_data.local.BlogDao
import com.structure.blog_data.mapper.*
import com.structure.blog_data.remote.BlogApi
import com.structure.blog_data.remote.dto.BlogDto
import com.structure.blog_domain.model.BlogModel
import com.structure.blog_domain.repository.BlogRepository
import com.structure.core.domain.model.BlogType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BlogRepositoryImpl(
    private val dao: BlogDao,
    private val api: BlogApi
) : BlogRepository {

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