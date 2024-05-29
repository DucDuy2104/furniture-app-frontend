package com.example.assignment.model

data class Cart(
    val cartId: Int?,
    val amount: Int?,
    val userId: Int?,
    val productId: Int?,
    val createdAt: String?,
    val updatedAt: String,
    val product: Product?
)


data class CreateCartResponse(
    val data: CreateCart
)


data class CreateCart(
    val createCart: Cart
)


data class GetCartsByUserResponse(
    val data: GetCartByUserId
)

data class GetCartByUserId(
    val getCartByUserId: List<Cart>
)


data class UpdateCartResponse(
    val data: UpdateCart
)

data class  UpdateCart(
    val updateCart: Boolean
)