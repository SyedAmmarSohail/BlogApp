package com.structure.blog_data.repository

import android.app.Application
import android.util.Log
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.structure.blog_data.adapter.FallbackEnum
import com.structure.blog_data.local.BlogDao
import com.structure.blog_data.mapper.*
import com.structure.blog_data.remote.BlogApi
import com.structure.blog_data.remote.dto.Article
import com.structure.blog_data.remote.dto.BlogDto
import com.structure.blog_domain.model.BlogModel
import com.structure.blog_domain.repository.BlogRepository
import com.structure.core.domain.model.BlogType
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.lang.reflect.Type

class BlogRepositoryImpl(
    private val dao: BlogDao,
    private val api: BlogApi,
    val moshi: Moshi,
    val context: Application
) : BlogRepository {

    override suspend fun getBlog(): Result<List<BlogModel>> {
        return try {
            delay(500)
            val blogResponse: BlogDto? = readJsonFile("blogs_response.json")
            Log.d("ReadJson:3: ", blogResponse.toString())
            blogResponse?.let {
                Result.success(
                    blogResponse.blogs
                        .mapNotNull { it.toBlogModel() }
                )
            } ?: Result.failure(Exception("Service Failed"))

//            TODO(uncomment below code if you have actual api)
//            val response = api.getBlogs()
//            if (response.isSuccessful) {
//                val blogDto = response.body() as BlogDto
//                Result.success(
//                    blogDto.blogs
//                        .mapNotNull { it.toBlogModel() }
//                )
//            } else {
//                Result.failure(Exception("Service Failed"))
//            }
        } catch (e: Exception) {
            Log.d("ReadJson:Error: ", e.message.toString())
            e.printStackTrace()
            Result.failure(e)
        }
    }


    override suspend fun insertBlogs(blogList: List<BlogModel>) {
        dao.insertBlogs(blogList.mapNotNull { it.toBlogEntity() })
    }

    override fun searchBlogsByType(blogType: BlogType): Flow<List<BlogModel>> {
        return dao.searchBlog(
            blogType.name
        ).map { entities ->
            entities.map { it.toBlogModel() }
        }
    }

//    private fun readJsonFile(jsonFile: String): BlogDto? {
//        Log.d("ReadJson:1: ", jsonFile)
//        val listType = Types.newParameterizedType(
//            BlogDto::class.java, Types.newParameterizedType(
//                List::class.java,
//                Article::class.java,
//            )
//        )
//        Log.d("ReadJson:2: ", listType.toString())
//        val adapter: JsonAdapter<BlogDto> = moshi.adapter(listType)
//        val blogJson =
//            context.assets.open(jsonFile).bufferedReader()
//                .use { it.readText() }
//        return adapter.fromJson(blogJson)
//    }

    inline fun <reified T> readJsonFile(jsonFile: String): T? {
        val adapter = moshi.adapter(T::class.java)
        val json = context.assets.open(jsonFile).bufferedReader().use { it.readText() }
        return adapter.fromJson(json)
    }
}