package com.example.task26.data.remote.network.mapper

import com.example.task26.data.remote.network.model.CategoryDto
import com.example.task26.domain.remote.model.GetCategory


fun CategoryDto.toDomainCategoryModel(): GetCategory {
    return GetCategory(
        bglNumber = bglNumber ?: "",
        bglVariant = bglVariant ?: "",
        children = children.map { x -> x.toDomainCategoryModel() },
        createdAt = createdAt,
        id = id,
        main = main ?: "",
        name = name,
        nameDe = nameDe,
        orderId = orderId ?: 0
    )
}