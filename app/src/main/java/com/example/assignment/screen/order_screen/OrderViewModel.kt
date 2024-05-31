package com.example.assignment.screen.order_screen

import DataStoreUtil
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment.retrofit.category.GrapQuery
import com.example.assignment.retrofit.order.OrderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OrderViewModel : ViewModel() {
    private val orderRepository = OrderRepository()

    private val _state = MutableStateFlow(OrderScreenState())
    val state = _state.asStateFlow()


    fun getOrderByUserId(context: Context) {
        val userId = DataStoreUtil(context).getUser()?.userId

        val query = """
            query {
              getOrderByUserId(getOrderByUserIdInput: {userId: $userId}) {
                orderId
                createdAt
              	orderItems{
                  orderItemId
                  amount
                  productId
                  product {
                    productId
                    price
                  }
                  
                }
                
              }
            }
        """.trimIndent()
        viewModelScope.launch{
            _state.update {
                it.copy(isLoading = true)
            }

            orderRepository.getOrderByUserId(GrapQuery(query)) {orders->
                _state.update {
                    it.copy(orders = orders)
                }
            }

            _state.update {
                it.copy(isLoading = false)
            }
        }

    }
}