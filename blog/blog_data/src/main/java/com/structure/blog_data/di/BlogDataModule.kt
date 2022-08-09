package com.structure.blog_data.di

import android.app.Application
import androidx.room.Room
import com.structure.blog_data.local.BlogDatabase
import com.structure.blog_data.remote.BlogApi
import com.structure.blog_data.repository.BlogRepositoryImpl
import com.structure.blog_domain.repository.BlogRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BlogDataModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideOpenFoodApi(client: OkHttpClient): BlogApi {
        return Retrofit.Builder()
            .baseUrl(BlogApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideTrackerDatabase(app: Application): BlogDatabase {
        return Room.databaseBuilder(
            app,
            BlogDatabase::class.java,
            "tracker_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideBlogRepository(
        api: BlogApi,
        db: BlogDatabase
    ): BlogRepository {
        return BlogRepositoryImpl(
            dao = db.dao,
            api = api
        )
    }
}