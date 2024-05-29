package com.example.assignment.retrofit.category

import android.util.Log
import com.example.assignment.model.Category
import com.example.assignment.retrofit.util.RetrofitInstance

class CategoryRepository {

    suspend fun getCategories(query: GrapQuery,onReturn: (List<Category>) -> Unit){
        val response = RetrofitInstance.categoryApi.getCatgories(query)

        val categories = response.body()?.data?.getAllCategory

        onReturn(categories ?: emptyList())
    }
}