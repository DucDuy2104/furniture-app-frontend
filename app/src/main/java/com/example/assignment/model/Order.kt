package com.example.assignment.model

data class Order(
    val userId: Int?,
    val createdSuccess: Boolean?,
    val orderItems: List<OrderItem>?
)


data class OrderItem(
    val orderItemId: Int?,
    val amount: Int?,
    val orderId: Int?,
    val productId: Int?,
    val product: Product?
)

data class CreateOrderResponse(
    val data: CreateOrder
)

data class CreateOrder (
    val createOrder: Order
)

data class GetOrderByUserIdResponse(
    val data: GetOrderByUserId
)

data class GetOrderByUserId(
    val getOrderByUserId: List<Order>
)


