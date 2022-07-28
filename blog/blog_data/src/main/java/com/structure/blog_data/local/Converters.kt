package com.structure.blog_data.local

import androidx.room.TypeConverter
import com.structure.core.domain.model.BlogType

class Converters {
    @TypeConverter
    fun fromBlogType(value: BlogType) = value.name

    @TypeConverter
    fun toBlogType(value: String) = enumValueOf<BlogType>(value)

}