package com.example.assignment.retrofit.auth

import com.example.assignment.model.LoginResponse
import com.example.assignment.model.RegisterResponse
import com.example.assignment.retrofit.category.GrapQuery
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("graphql")
    suspend fun register(@Body query: GrapQuery) : Response<RegisterResponse>

    @POST("graphql")
    suspend fun login(@Body query: GrapQuery) : Response<LoginResponse>

}