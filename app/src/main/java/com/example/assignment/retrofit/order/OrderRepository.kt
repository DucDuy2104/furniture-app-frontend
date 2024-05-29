package com.example.assignment.retrofit.order

import com.example.assignment.model.Order
import com.example.assignment.retrofit.category.GrapQuery
import com.example.assignment.retrofit.util.RetrofitInstance

class OrderRepository {
    private val orderApi = RetrofitInstance.orderApi

    suspend fun createOrder(query: GrapQuery, onComplete: (Boolean) -> Unit) {
        val response = orderApi.createOrder(query)
        val isCreateSuccess = response.body()?.data?.createOrder?.createdSuccess

        if (isCreateSuccess != null) {
            onComplete(isCreateSuccess)
        }
    }

    suspend fun getOrderByUserId(query: GrapQuery, onComplete: (List<Order>) -> Unit) {
        val response = orderApi.getOrderByUserId(query)
        val orders = response.body()?.data?.getOrderByUserId

        if (orders != null) {
            onComplete(orders)
        }
    }


}