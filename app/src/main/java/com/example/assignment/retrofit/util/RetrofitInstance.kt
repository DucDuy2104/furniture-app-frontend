package com.example.assignment.retrofit.util

import com.example.assignment.retrofit.auth.AuthApi
import com.example.assignment.retrofit.cart.CartApi
import com.example.assignment.retrofit.category.CategoryApi
import com.example.assignment.retrofit.order.OrderApi
import com.example.assignment.retrofit.product.ProductApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val ipV4 = "192.168.1.33"
    private val BASE_URL = "http://$ipV4:3000/"

    val retrofit: Retrofit by lazy {
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val categoryApi: CategoryApi by lazy {
        retrofit.create(CategoryApi::class.java)
    }

    val productApi: ProductApi by lazy {
        retrofit.create(ProductApi::class.java)
    }

    val authApi: AuthApi by lazy {
        retrofit.create(AuthApi::class.java)
    }

    val cartApi: CartApi by lazy {
        retrofit.create(CartApi::class.java)
    }

    val orderApi: OrderApi by lazy {
        retrofit.create(OrderApi::class.java)
    }
}