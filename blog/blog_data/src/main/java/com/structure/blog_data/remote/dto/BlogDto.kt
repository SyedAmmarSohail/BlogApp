package com.structure.blog_data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * uncomment below moshi adapter for live api
 * */
@JsonClass(generateAdapter = true)
data class BlogDto(
    @field:Json(name = "blogs")
    val blogs: List<Article>,
)