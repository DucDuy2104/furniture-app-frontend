package com.example.assignment.screen.home_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment.retrofit.category.CategoryRepository
import com.example.assignment.retrofit.category.GrapQuery
import com.example.assignment.retrofit.product.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    private val categoryRepository = CategoryRepository()
    private val productRepository = ProductRepository()

    private val _state= MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()

    var categoryQuery = """
        query {
          getAllCategory {
        		categoryId
            title
            icon
          }
        }
    """.trimIndent()

    var productQuery = """
        query {
        	getAllProduct {
            productId
            name
            price
            image
          }
        }
    """.trimIndent()

    init {
        getCategory(GrapQuery(categoryQuery), GrapQuery(productQuery))
    }



    private fun getCategory(categoryQuery: GrapQuery, productQuery: GrapQuery) {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }

            categoryRepository.getCategories(categoryQuery) { categories ->

                Log.d("Duy", "Categories received: $categories")
                _state.update {
                    it.copy(categories = categories)
                }
            }

            productRepository.getProducts(productQuery) {products ->

                Log.d("Duy", "Products received:  $products")
                _state.update {
                    it.copy(products = products)
                }

            }

            _state.update {
                it.copy(isLoading = false)
            }
        }
    }
}