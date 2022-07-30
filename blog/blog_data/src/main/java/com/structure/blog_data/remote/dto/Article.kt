package com.structure.blog_data.remote.dto

import com.squareup.moshi.Json
import com.structure.core.domain.model.BlogType

data class Article(
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "description")
    val description: String,
    @field:Json(name = "imageUrl")
    val imageUrl: String,
    @field:Json(name = "type")
    val type: BlogType,
    @field:Json(name = "date")
    val date: String
)