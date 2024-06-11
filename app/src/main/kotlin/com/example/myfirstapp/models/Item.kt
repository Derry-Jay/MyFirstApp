package com.example.myfirstapp.models

import com.google.gson.annotations.SerializedName


data class Item(
    @SerializedName("_id") var id: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("price") var price: Int? = null,
    @SerializedName("category") var category: ItemCategory? = ItemCategory(),
    @SerializedName("description") var description: String? = null,
    @SerializedName("createdBy") var createdBy: User? = User(),
    @SerializedName("createdAt") var createdAt: String? = null,
    @SerializedName("updatedAt") var updatedAt: String? = null,
    @SerializedName("slug") var slug: String? = null,
    @SerializedName("image") var image: String? = null,
) {
    override fun equals(other: Any?): Boolean {
        return (other is Item) && (this.id == other.id)
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (price ?: 0)
        result = 31 * result + (category?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (createdBy?.hashCode() ?: 0)
        result = 31 * result + (createdAt?.hashCode() ?: 0)
        result = 31 * result + (updatedAt?.hashCode() ?: 0)
        result = 31 * result + (slug?.hashCode() ?: 0)
        result = 31 * result + (image?.hashCode() ?: 0)
        return result
    }
}