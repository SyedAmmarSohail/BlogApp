package com.structure.blog_data.remote

import com.structure.blog_data.remote.dto.BlogDto
import retrofit2.Response
import retrofit2.http.GET

interface BlogApi {

    @GET("get-blogs")
    suspend fun getBlogs(
    ): Response<BlogDto>

    companion object {
        const val BASE_URL = "https://run.mocky.io/"
    }
}