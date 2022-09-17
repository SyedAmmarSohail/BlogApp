package com.structure.blog_domain.use_case

data class BlogUseCases(
    val getBlog : GetBlog,
    val searchBlog: SearchBlog,
    val storeBlogs: StoreBlogs
)
