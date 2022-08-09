package com.structure.blog_data.remote

import com.structure.blog_data.remote.dto.BlogDto
import retrofit2.Response
import retrofit2.http.GET

interface BlogApi {

    @GET("v3/b0b5d515-e4f5-42ca-b424-5ccfff2e2083")
    suspend fun getBlogs(
    ): Response<BlogDto>

    companion object {
        const val BASE_URL = "https://run.mocky.io/"
    }
}