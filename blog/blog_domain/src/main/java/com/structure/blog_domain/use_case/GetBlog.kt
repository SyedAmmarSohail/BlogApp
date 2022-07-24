package com.structure.blog_domain.use_case

import com.structure.blog_domain.model.BlogModel
import com.structure.blog_domain.model.TrackedFood
import com.structure.blog_domain.repository.BlogRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class GetBlog(
    private val repository: BlogRepository
) {

    operator fun invoke(): Flow<List<BlogModel>> {
        return repository.getBlog()
    }
}