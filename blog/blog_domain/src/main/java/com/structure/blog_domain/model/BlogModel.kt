package com.structure.blog_domain.model

import com.structure.core.domain.model.BlogType

data class BlogModel(
    val id  : String,
    val title : String,
    val description : String,
    val imageUrl : String,
    val link : String,
    val type : BlogType,
    val date : String
)

