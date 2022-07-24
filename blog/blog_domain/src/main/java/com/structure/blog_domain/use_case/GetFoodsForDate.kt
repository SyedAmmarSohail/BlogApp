package com.structure.blog_domain.use_case

import com.structure.blog_domain.model.TrackedFood
import com.structure.blog_domain.repository.BlogRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class GetFoodsForDate(
    private val repository: BlogRepository
) {

    operator fun invoke(date: LocalDate): Flow<List<TrackedFood>> {
        return repository.getFoodsForDate(date)
    }
}