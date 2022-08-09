package com.structure.blog_domain.use_case

import com.structure.blog_domain.model.BlogModel
import com.structure.blog_domain.repository.BlogRepository
import com.structure.core.domain.model.BlogType
import kotlinx.coroutines.flow.Flow

class SearchBlog(
    private val repository: BlogRepository
) {

    operator fun invoke(blogType: BlogType): Flow<List<BlogModel>> {
        return repository.searchBlogsByType(blogType)
    }
}