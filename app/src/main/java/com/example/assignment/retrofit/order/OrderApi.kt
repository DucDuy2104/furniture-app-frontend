package com.example.assignment.retrofit.order

import com.example.assignment.model.CreateOrderResponse
import com.example.assignment.model.GetOrderByUserIdResponse
import com.example.assignment.retrofit.category.GrapQuery
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface OrderApi {

    @POST("graphql")
    suspend fun createOrder(@Body query: GrapQuery) : Response<CreateOrderResponse>

    @POST("graphql")
    suspend fun getOrderByUserId(@Body query: GrapQuery) : Response<GetOrderByUserIdResponse>
}