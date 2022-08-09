package com.structure.blog_domain.use_case

import com.structure.blog_domain.model.BlogModel
import com.structure.blog_domain.repository.BlogRepository

class GetBlog(
    private val repository: BlogRepository
) {

    suspend operator fun invoke(): Result<List<BlogModel>> {
        return repository.getBlog()
    }
}