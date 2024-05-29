package com.example.assignment.retrofit.cart

import com.example.assignment.model.Cart
import com.example.assignment.retrofit.category.GrapQuery
import com.example.assignment.retrofit.util.RetrofitInstance

class CartRepository {

    private val cartApi = RetrofitInstance.cartApi
    suspend fun createCart(query: GrapQuery, onCreateComplete: (Cart) -> Unit) {
        val response = cartApi.createCart(query)
        val cart = response.body()?.data?.createCart
        if (cart != null) {
            onCreateComplete(cart)
        }
    }

    suspend fun getCartByUserId(query: GrapQuery, onComplete: (List<Cart>) -> Unit) {
        val response = cartApi.getCartsByUseId(query)
        val carts = response.body()?.data?.getCartByUserId


        if (carts != null) {
            onComplete(carts)
        }
    }

    suspend fun updateCart(query: GrapQuery, onComplete:(Boolean) -> Unit) {
        val response = cartApi.updateCart(query)
        val isSuccess = response.body()?.data?.updateCart
        if (isSuccess != null) {
            onComplete(isSuccess)
        }
    }
}