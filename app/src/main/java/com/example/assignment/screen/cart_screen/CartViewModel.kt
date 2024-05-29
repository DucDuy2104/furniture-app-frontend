package com.example.assignment.screen.cart_screen

import DataStoreUtil
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment.retrofit.cart.CartRepository
import com.example.assignment.retrofit.category.GrapQuery
import com.example.assignment.retrofit.order.OrderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.log

class CartViewModel : ViewModel() {
    private val cartRepository = CartRepository()
    private val orderRepository = OrderRepository()

    private val _state = MutableStateFlow(CartScreenState())
    val state = _state.asStateFlow()

    fun getCarts(context: Context) {

        val dataStoreUtil = DataStoreUtil(context)

        val query = """
            query {
              getCartByUserId(getCartByUserIdInput: {userId: ${dataStoreUtil.getUser()?.userId}}) {
                cartId
                amount
                product {
                  productId
                  price
                  name
                  image
                }
              }
            }

        """.trimIndent()


        viewModelScope.launch {
            if (!state.value.isLoading) {
                _state.update {
                    it.copy(isLoading = true)
                }
            }

            cartRepository.getCartByUserId(GrapQuery(query)) { carts ->
                _state.update {
                    it.copy(carts = carts)
                }
            }

            _state.update {
                it.copy(isLoading = false)
            }
        }

    }

    fun updateCart(cartId: Int, type: String, context: Context) {
        val query = """
            mutation {
              updateCart(updateCartInput: {cartId: $cartId, type: "$type"})
            }
        """.trimIndent()

        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            cartRepository.updateCart(GrapQuery(query)) {isSuccess->
                if (isSuccess) {
                    getCarts(context)
                }
            }
        }
    }

    fun createOrder(context: Context) {
        val dataStoreUtil = DataStoreUtil(context)
        val userId = dataStoreUtil.getUser()?.userId


        val query = """
            mutation {
              createOrder(createOrderInput: {userId: $userId}) {
                createdSuccess
               }
            }
        """.trimIndent()

        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }

            orderRepository.createOrder(GrapQuery(query)) {isSuccess->
                if (isSuccess) {
                    Toast.makeText(context, "Order success!", Toast.LENGTH_SHORT).show()
                    getCarts(context)
                }
            }
        }
    }
}