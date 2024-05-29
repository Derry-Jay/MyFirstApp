package com.example.myfirstapp.models

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