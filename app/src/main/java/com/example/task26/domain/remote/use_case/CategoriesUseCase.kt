package com.example.task26.domain.remote.use_case

import javax.inject.Inject

data class CategoriesUseCase @Inject constructor(
    val getCategoriesUseCase: GetCategoriesUseCase,
    val getFilteredCategoriesUseCase: GetFilteredCategoriesUseCase
)
