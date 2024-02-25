package com.example.task26.data.remote.network.service

import com.example.task26.data.remote.network.model.CategoryDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoriesService {
    @GET("6f722f19-3297-4edd-a249-fe765e8104db")
    suspend fun getCategories(@Query("search") search: String? = null): Response<List<CategoryDto>>
}