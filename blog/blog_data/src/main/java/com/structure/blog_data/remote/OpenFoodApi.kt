package com.structure.blog_data.remote

import com.structure.blog_data.remote.dto.BlogDto
import com.structure.blog_data.remote.dto.SearchDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenFoodApi {

    @GET("cgi/search.pl?search_simple=1&json=1&action=process&fields=product_name,nutriments,image_front_thumb_url")
    suspend fun searchFood(
        @Query("search_terms") query: String,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): SearchDto

    @GET("https://run.mocky.io/v3/b0b5d515-e4f5-42ca-b424-5ccfff2e2083")
    suspend fun getBlogs(
    ): Response<BlogDto>

    companion object {
        const val BASE_URL = "https://us.openfoodfacts.org/"
    }
}