package com.example.assignment.screen.detail_screen

import com.example.assignment.model.Cart
import com.example.assignment.model.Product

data class DetailScreenState (
    val isLoading: Boolean = false,
    val product: Product? = null,
    val amount: Int? = 0,
    val cart: Cart? = null
)