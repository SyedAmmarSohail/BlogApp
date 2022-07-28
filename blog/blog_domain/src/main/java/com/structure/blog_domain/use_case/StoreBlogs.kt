package com.structure.blog_domain.use_case

import com.structure.blog_domain.model.BlogModel
import com.structure.blog_domain.repository.BlogRepository

class StoreBlogs(
    val repository: BlogRepository
) {

    suspend operator fun invoke(blogList : List<BlogModel>){
        repository.insertBlogs(blogList)
    }
}