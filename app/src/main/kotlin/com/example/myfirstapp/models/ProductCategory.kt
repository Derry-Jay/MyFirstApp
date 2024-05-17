package com.example.myfirstapp.models


data class ProductCategory(
    val id: Int, val name: String, val image: String, val createdAt: String, val updatedAt: String
) {
    override fun equals(other: Any?): Boolean {
        return (other is ProductCategory) && (this.id == other.id)
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + image.hashCode()
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + updatedAt.hashCode()
        return result
    }
}