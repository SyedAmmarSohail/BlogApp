package com.structure.blog_data.remote.dto

import com.structure.core.domain.model.BlogType

data class Article(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val link: String,
    val type: BlogType,
    val date: String
)