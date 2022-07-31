package com.structure.blog_presentation.blog_overview

import com.structure.blog_domain.model.BlogModel

data class BlogOverviewState(
    val isSearching : Boolean = false,
    val blogs : List<BlogModel> = emptyList(),
    val searchKey : String = "",
    val isHintVisible : Boolean = false
)
