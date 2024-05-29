package com.example.assignment.screen.cart_screen

import com.example.assignment.model.Cart

data class CartScreenState (
    val isLoading: Boolean = false,
    val carts : List<Cart>? = null
)