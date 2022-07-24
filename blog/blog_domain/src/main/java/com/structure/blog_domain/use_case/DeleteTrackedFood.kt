package com.structure.blog_domain.use_case

import com.structure.blog_domain.model.TrackedFood
import com.structure.blog_domain.repository.BlogRepository

class DeleteTrackedFood(
    private val repository: BlogRepository
) {

    suspend operator fun invoke(trackedFood: TrackedFood) {
        repository.deleteTrackedFood(trackedFood)
    }
}