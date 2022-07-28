package com.structure.blog_data.mapper

import com.structure.blog_data.local.entity.BlogEntity
import com.structure.blog_data.remote.dto.Article
import com.structure.blog_domain.model.BlogModel
import com.structure.core.domain.model.BlogType

fun BlogModel.toBlogEntity(): BlogEntity {
    return BlogEntity(
        title = this.title,
        description = this.description,
        image = this.imageUrl,
        type = this.type.name,
        date = this.date
    )
}

fun Article.toBlogModel(): BlogModel {
    return BlogModel(
        this.title,
        this.description,
        this.imageUrl,
        this.type,
        this.date
    )
}

fun BlogEntity.toBlogModel(): BlogModel {
    return BlogModel(
        this.title,
        this.description,
        this.image,
        BlogType.valueOf(this.type),
        this.date
    )
}