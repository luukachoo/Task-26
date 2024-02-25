package com.example.task26.domain.remote.use_case

import com.example.task26.domain.remote.repository.CategoryRepository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(private val repository: CategoryRepository) {
    operator fun invoke() = repository.getCategories()
}