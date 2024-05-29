package com.example.assignment.screen.order_screen

import com.example.assignment.model.Order

data class OrderScreenState(
    val isLoading: Boolean = false,
    val orders: List<Order>? = null
)