package com.example.assignment.screen.home_screen

import com.example.assignment.model.Category
import com.example.assignment.model.Product

data class HomeScreenState (
    val isLoading: Boolean = false,
    val categories: List<Category> = emptyList(),
    val products: List<Product> = emptyList()
)