package com.example.assignment.retrofit.product

import android.util.Log
import com.example.assignment.model.Product
import com.example.assignment.retrofit.category.GrapQuery
import com.example.assignment.retrofit.util.RetrofitInstance

class ProductRepository {
    suspend fun getProducts(query: GrapQuery,getProducts: (List<Product>) -> Unit) {
        val response = RetrofitInstance.productApi.getProducts(query)
        val products = response.body()?.data?.getAllProduct
        Log.d("Duy", "getProducts: ...")
        getProducts(products ?: emptyList())
    }

    suspend fun getProduct(query: GrapQuery, getProduct: (Product) -> Unit) {
        val response =  RetrofitInstance.productApi.getProduct(query)
        val product = response.body()?.data?.getProductById
        if (product != null) {
            getProduct(product)
        }
    }
}