package com.example.task26.domain.remote.repository

import com.example.task26.data.remote.utils.Resource
import com.example.task26.domain.remote.model.GetCategory
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategories(): Flow<Resource<List<GetCategory>>>
    fun getCategoryByTitle(title: String): Flow<Resource<List<GetCategory>>>
}