package com.example.assignment.screen.detail_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment.retrofit.cart.CartRepository
import com.example.assignment.retrofit.category.GrapQuery
import com.example.assignment.retrofit.product.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.log

class DetailViewModel : ViewModel() {

    private val productRepository = ProductRepository()
    private  val cartRepository = CartRepository()

    private val _state = MutableStateFlow(DetailScreenState())
    val state = _state.asStateFlow()

    fun initId(productId : Int) {
        val query = """
            query {
              getProductById(getProductByIdInput: {productId: ${productId}}) {
                productId
                name
                price
                description
                image
                rate
                vote
              }
            }
        """.trimIndent()

        getProduct(query = GrapQuery(query =  query))
    }

    fun addAmount() {
        viewModelScope.launch {
            _state.update {
                it.copy(amount = state.value.amount?.plus(1))
            }
        }
    }

    fun minusAmount() {
        viewModelScope.launch {
            if (state.value.amount!! > 0) {
                _state.update {
                    it.copy(amount = state.value.amount?.minus(1))
                }
            }
        }
    }

    private fun getProduct(query: GrapQuery) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            productRepository.getProduct(query =query) {product ->
                _state.update {
                    it.copy(product = product)
                }
            }


            _state.update {
                it.copy(
                    isLoading = false
                )
            }
        }
    }

    fun resetCartState() {
        viewModelScope.launch {
            _state.update {
                it.copy(cart = null, amount = 0)
            }
        }
    }


    fun createCart(productId: Int, userId: Int, amount: Int){
        val query = """
            mutation {
              createCart(createCartInput: {productId: $productId, userId: $userId, amount: $amount}) {
                cartId
                productId
                userId
                amount
                createdAt
              }
            }
        """.trimIndent()

        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }

            cartRepository.createCart(GrapQuery(query)) {cart ->
                Log.d("Duy", "createCart: $cart")
                _state.update {
                    it.copy(cart = cart)
                }
            }

            _state.update {
                it.copy(isLoading = false)
            }
        }
    }



}