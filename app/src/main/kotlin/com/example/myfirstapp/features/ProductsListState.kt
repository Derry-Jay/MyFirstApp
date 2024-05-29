package com.example.myfirstapp.features

import com.example.myfirstapp.models.Product

sealed class ProductsListState {
    object Initial : ProductsListState()
    data class Success(val data: List<Product>) : ProductsListState()
    data class Error(val message: String) : ProductsListState()
}
