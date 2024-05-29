package com.example.assignment.model

data class Category(
    val categoryId: Int?,
    val title: String?,
    val icon: String?
)

data class CategoryResponse(
    val data: GetAllCategory
)


data class GetAllCategory(
    val getAllCategory: List<Category>
)