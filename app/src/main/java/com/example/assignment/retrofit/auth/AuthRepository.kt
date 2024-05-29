package com.example.assignment.retrofit.auth

import android.util.Log
import com.example.assignment.model.User
import com.example.assignment.retrofit.category.GrapQuery
import com.example.assignment.retrofit.util.RetrofitInstance

class AuthRepository {
    suspend fun register(query: GrapQuery , onRegisterComplete: (Boolean) -> Unit) {
        val response =  RetrofitInstance.authApi.register(query)
        val isSuccess = response.body()?.data?.userRegister
        onRegisterComplete(isSuccess ?: false)
    }

    suspend fun login(query: GrapQuery, onLoginSuccess: (User) -> Unit) {
        val response = RetrofitInstance.authApi.login(query)

        Log.d("Duy", "login: $response")
        val user = response.body()?.data?.userLogin

        if (user != null) {
            onLoginSuccess(user)
        }
    }
}