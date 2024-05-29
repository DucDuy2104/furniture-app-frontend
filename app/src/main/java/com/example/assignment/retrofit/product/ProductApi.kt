package com.example.assignment.retrofit.product


import com.example.assignment.model.ProductResponse
import com.example.assignment.model.ProductsResponse
import com.example.assignment.retrofit.category.GrapQuery
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ProductApi {

    @POST("graphql")
    suspend fun getProducts(@Body query: GrapQuery) : Response<ProductsResponse>

    @POST("graphql")
    suspend fun getProduct(@Body query: GrapQuery) : Response<ProductResponse>


}