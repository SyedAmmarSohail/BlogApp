package com.structure.blog_presentation.search

import com.structure.blog_domain.model.TrackableFood

data class TrackableFoodUiState(
    val food: TrackableFood,
    val isExpanded: Boolean = false,
    val amount: String = ""
)
