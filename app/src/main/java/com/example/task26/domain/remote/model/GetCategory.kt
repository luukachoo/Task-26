package com.example.task26.domain.remote.model

data class GetCategory(
    val bglNumber: String,
    val bglVariant: String,
    val children: List<GetCategory>,
    val createdAt: String,
    val id: String,
    val main: String,
    val name: String,
    val nameDe: String,
    val orderId: Int
)
