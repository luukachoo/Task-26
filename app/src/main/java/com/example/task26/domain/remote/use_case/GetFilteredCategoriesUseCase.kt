package com.example.task26.domain.remote.use_case

import com.example.task26.data.remote.network.mapper.base.asResource
import com.example.task26.data.remote.utils.Resource
import com.example.task26.domain.remote.model.GetCategory
import com.example.task26.domain.remote.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFilteredCategoriesUseCase @Inject constructor(private val repository: CategoryRepository) {
    operator fun invoke(title: String): Flow<Resource<List<GetCategory>>> {
        val categories =
            repository.getCategories()
        return categories.asResource {
            it.filter { getCategory ->
                getCategory.name.contains(title, ignoreCase = true)
            }
        }
    }
}

