package com.structure.blog_data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.structure.core.domain.model.BlogType
import java.util.*

@Entity
data class BlogEntity(
    @PrimaryKey val id : String = UUID.randomUUID().toString(),
    val title : String,
    val description : String,
    val image : String,
    val type : String,
    val date : String
)

