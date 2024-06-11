package com.example.myfirstapp.models

import com.example.myfirstapp.data.gson

data class ProductBase(
    val products: List<Product>,
) {
    fun ProductCategory.fromJSON(body: String): List<Product> {
        return try {
            gson.fromJson(body, Array<Product>::class.java).toList()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}
