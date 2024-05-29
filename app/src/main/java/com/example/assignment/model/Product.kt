package com.example.assignment.model

data class Product(
    val productId: Int?,
    val name: String?,
    val price: Double?,
    val description: String?,
    val image: String?,
    val rate: Double?,
    val vote: Int?,
    val categoryId: Int?,
    val createdAt: String?,
    val updatedAt: String?,
    val category: Category?
)


data class ProductsResponse(
    val data: GetAllProduct
)


data class ProductResponse(
    val data: GetProductById
)

data class GetAllProduct(
    val getAllProduct: List<Product>
)

data class GetProductById(
    val getProductById: Product
)