package com.structure.blog_domain.use_case

import com.structure.blog_domain.model.TrackableFood
import com.structure.blog_domain.repository.BlogRepository

class SearchFood(
    private val repository: BlogRepository
) {

    suspend operator fun invoke(
        query: String,
        page: Int = 1,
        pageSize: Int = 40
    ): Result<List<TrackableFood>> {
        if(query.isBlank()) {
            return Result.success(emptyList())
        }
        return repository.searchFood(query.trim(), page, pageSize)
    }
}