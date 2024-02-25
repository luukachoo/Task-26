package com.example.task26.data.remote.repository

import com.example.task26.data.remote.network.mapper.base.asResource
import com.example.task26.data.remote.network.mapper.toDomainCategoryModel
import com.example.task26.data.remote.network.service.CategoriesService
import com.example.task26.data.remote.utils.Resource
import com.example.task26.data.remote.utils.ResponseHandler
import com.example.task26.domain.remote.model.GetCategory
import com.example.task26.domain.remote.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val service: CategoriesService,
    private val responseHandler: ResponseHandler
) : CategoryRepository {
    override fun getCategories(): Flow<Resource<List<GetCategory>>> {
        return responseHandler.handleApiCall {
            service.getCategories()
        }.asResource { it.map { dto -> dto.toDomainCategoryModel() } }
    }

    override fun getCategoryByTitle(title: String): Flow<Resource<List<GetCategory>>> {
        return responseHandler.handleApiCall {
            service.getCategories(search = title)
        }.asResource { it.map { dto -> dto.toDomainCategoryModel() } }
    }
}