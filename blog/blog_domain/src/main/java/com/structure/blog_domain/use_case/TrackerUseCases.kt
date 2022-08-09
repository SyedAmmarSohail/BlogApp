package com.structure.blog_domain.use_case

data class TrackerUseCases(
    val getBlog : GetBlog,
    val searchBlog: SearchBlog,
    val storeBlogs: StoreBlogs
)
