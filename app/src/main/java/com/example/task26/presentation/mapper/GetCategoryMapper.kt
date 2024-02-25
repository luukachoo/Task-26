package com.example.task26.presentation.mapper

import com.example.task26.domain.remote.model.GetCategory
import com.example.task26.presentation.model.Category

fun GetCategory.toPresentationCategoryModel(): Category {
    return Category(
        children = children.size,
        id = id,
        name = name,
    )
}