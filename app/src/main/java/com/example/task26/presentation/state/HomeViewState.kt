package com.example.task26.presentation.state

import com.example.task26.presentation.model.Category

data class HomeViewState(
    val isLoading: Boolean = false,
    val categories: List<Category>? = emptyList(),
    val errorMessage: String? = null
)
