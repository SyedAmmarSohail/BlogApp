package com.structure.blog_data.mapper

import com.structure.blog_data.local.entity.BlogEntity
import com.structure.blog_data.remote.dto.Article
import com.structure.blog_domain.model.BlogModel
import com.structure.core.domain.model.BlogType

fun BlogModel.toBlogEntity(): BlogEntity {
    return BlogEntity(
        id = this.id,
        title = this.title,
        description = this.description,
        image = this.imageUrl,
        link = this.link,
        type = this.type,
        date = this.date
    )
}

fun Article.toBlogModel(): BlogModel {
    return BlogModel(
        this.id,
        this.title,
        this.description,
        this.imageUrl,
        this.link,
        BlogType.valueOf(this.blogType),
        this.date
    )
}

fun BlogEntity.toBlogModel(): BlogModel {
    return BlogModel(
        this.id,
        this.title,
        this.description,
        this.image,
        this.link,
        this.type,
        this.date
    )
}