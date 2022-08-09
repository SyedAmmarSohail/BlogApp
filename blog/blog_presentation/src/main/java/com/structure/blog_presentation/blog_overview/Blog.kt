package com.structure.blog_presentation.blog_overview

import com.structure.blog_domain.model.BlogModel
import com.structure.core.domain.model.BlogType

val blogModel = BlogModel(
    "id",
    "title",
    "description",
    "imageUrl",
    BlogType.FEATURED,
    "date"
)

val dummyBlogList = listOf(blogModel, blogModel, blogModel, blogModel)
