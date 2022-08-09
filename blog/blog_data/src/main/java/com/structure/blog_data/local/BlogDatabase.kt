package com.structure.blog_data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.structure.blog_data.local.entity.BlogEntity

@Database(
    entities = [BlogEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class BlogDatabase: RoomDatabase() {

    abstract val dao: BlogDao
}