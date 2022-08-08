package com.structure.blog_presentation.factory

import com.structure.blog_domain.model.BlogModel
import com.structure.core.domain.model.BlogType

val testBlogModel = BlogModel(
    id = "id",
    title = "title",
    description = "description",
    imageUrl = "image-url",
    type = BlogType.FEATURED,
    date = "date"
)

val testBlogList = listOf(
    testBlogModel,
    testBlogModel,
    testBlogModel,
    testBlogModel
)