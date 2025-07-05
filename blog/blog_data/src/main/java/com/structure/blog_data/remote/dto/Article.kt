package com.structure.blog_data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.structure.core.domain.model.BlogType

/**
 * uncomment below moshi adapter for live api
 * */
@JsonClass(generateAdapter = true)
data class Article(
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "description")
    val description: String,
    @field:Json(name = "imageUrl")
    val imageUrl: String,
    @field:Json(name = "link")
    val link: String,
    @field:Json(name = "blogType")
    val blogType: String,
    @field:Json(name = "date")
    val date: String
)