package com.example.assignment.retrofit.cart

import com.example.assignment.model.CreateCartResponse
import com.example.assignment.model.DeleteCart
import com.example.assignment.model.DeleteCartResponse
import com.example.assignment.model.GetCartsByUserResponse
import com.example.assignment.model.UpdateCartResponse
import com.example.assignment.retrofit.category.GrapQuery
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CartApi {


    @POST("graphql")
    suspend fun createCart(@Body query: GrapQuery) : Response<CreateCartResponse>

    @POST("graphql")
    suspend fun getCartsByUseId(@Body query: GrapQuery) : Response<GetCartsByUserResponse>

    @POST("graphql")
    suspend fun updateCart(@Body query: GrapQuery) : Response<UpdateCartResponse>


    @POST("graphql")
    suspend fun deleteCart(@Body query: GrapQuery) : Response<DeleteCartResponse>
}