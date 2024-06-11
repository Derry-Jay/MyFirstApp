package com.example.myfirstapp.models

import com.google.gson.Gson

data class Product(
    val id: Int,
    val title: String,
    val price: Int,
    val description: String,
    val images: List<String>,
    val createdAt: String,
    val updatedAt: String,
    val category: ProductCategory,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val brand: String,
    val thumbnail: String,
) {

    fun Product.fromJsonUsingGson(json: String): Product {
        return try {
            Gson().fromJson(json, this::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            Product(
                id,
                title,
                price,
                description,
                images,
                createdAt,
                updatedAt,
                category,
                discountPercentage,
                rating,
                stock,
                brand,
                thumbnail
            )
        }
    }

    fun Product.fromJsonUsingMoshi(json: String): Product {
        return com.squareup.moshi.Moshi.Builder().build().adapter(this::class.java).fromJson(json)
            ?: Product(
                id,
                title,
                price,
                description,
                images,
                createdAt,
                updatedAt,
                category,
                discountPercentage,
                rating,
                stock,
                brand,
                thumbnail
            )
    }

    override fun equals(other: Any?): Boolean {
        return (other is Product) && (this.id == other.id)
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + title.hashCode()
        result = 31 * result + price
        result = 31 * result + description.hashCode()
        result = 31 * result + images.hashCode()
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + updatedAt.hashCode()
        result = 31 * result + category.hashCode()
        result = 31 * result + discountPercentage.hashCode()
        result = 31 * result + rating.hashCode()
        result = 31 * result + stock
        result = 31 * result + brand.hashCode()
        result = 31 * result + thumbnail.hashCode()
        return result
    }
}