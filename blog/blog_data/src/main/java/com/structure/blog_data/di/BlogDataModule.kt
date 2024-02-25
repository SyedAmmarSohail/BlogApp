package com.structure.blog_data.di

import android.app.Application
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.structure.blog_data.adapter.FallbackEnum
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
    fun provideBlogApi(client: OkHttpClient, moshi: Moshi): BlogApi {
        return Retrofit.Builder()
            .baseUrl(BlogApi.BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideBlogDatabase(app: Application): BlogDatabase {
        return Room.databaseBuilder(
            app,
            BlogDatabase::class.java,
            BlogDatabase::class.java.simpleName
        ).build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(FallbackEnum.ADAPTER_FACTORY)
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideBlogRepository(
        api: BlogApi,
        db: BlogDatabase,
        moshi : Moshi,
        context : Application
    ): BlogRepository {
        return BlogRepositoryImpl(
            dao = db.dao,
            api = api,
            moshi = moshi,
            context = context
        )
    }
}