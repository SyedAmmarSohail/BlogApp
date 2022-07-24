package com.structure.blog_data.local.entity

import androidx.room.Entity

@Entity
data class BlogEntity(
    val title : String,
    val description : String,
    val date : String,
    val image : String
)
