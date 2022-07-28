package com.structure.blog_data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.structure.blog_data.local.entity.BlogEntity
import com.structure.blog_data.local.entity.TrackedFoodEntity

@Database(
    entities = [TrackedFoodEntity::class, BlogEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class TrackerDatabase: RoomDatabase() {

    abstract val dao: TrackerDao
}