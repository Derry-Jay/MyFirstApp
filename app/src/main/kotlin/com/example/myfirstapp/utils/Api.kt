package com.example.myfirstapp.utils

//import retrofit2.Response
import com.example.myfirstapp.models.CityBase
import com.example.myfirstapp.models.ItemBase
import com.example.myfirstapp.models.Product
import com.example.myfirstapp.models.ProductBase
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("/cities")
    fun obtainCities(): Call<CityBase>

    @GET("products")
    fun getProducts(): Call<ProductBase>

    @GET("products/{id}")
    fun getProductDetails(@Path("id") id: Int): Call<Product>

    @GET("products")
    fun getItems(): Call<ItemBase>
}