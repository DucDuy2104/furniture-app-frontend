package com.example.assignment.retrofit.category

import com.example.assignment.model.CategoryResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CategoryApi {
    @POST("graphql")
    suspend fun getCatgories(@Body query: GrapQuery): Response<CategoryResponse>
}


data class GrapQuery(
    val query: String
)