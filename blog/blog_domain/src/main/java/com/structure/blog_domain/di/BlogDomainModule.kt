package com.structure.blog_domain.di

import com.structure.core.domain.preferences.Preferences
import com.structure.blog_domain.repository.BlogRepository
import com.structure.blog_domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object BlogDomainModule {

    @ViewModelScoped
    @Provides
    fun provideBlogUseCases(
        repository: BlogRepository,
        preferences: Preferences
    ): BlogUseCases {
        return BlogUseCases(
            getBlog = GetBlog(repository),
            searchBlog = SearchBlog(repository),
            storeBlogs = StoreBlogs(repository)
        )
    }
}