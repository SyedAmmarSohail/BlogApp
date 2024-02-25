package com.structure.core.domain.model

import com.squareup.moshi.JsonClass

enum class BlogType(val value: String) {
    FEATURED("Featured"), LATEST("Latest"), TRENDING("Trending"), UNKNOWN("UNKNOWN")
}