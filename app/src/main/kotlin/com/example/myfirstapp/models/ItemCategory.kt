package com.example.myfirstapp.models

import com.google.gson.annotations.SerializedName


data class ItemCategory(

    @SerializedName("_id") var Id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("slug") var slug: String? = null

) {
    override fun equals(other: Any?): Boolean {
        return (other is ItemCategory) && (this.Id == other.Id)
    }

    override fun hashCode(): Int {
        var result = Id?.hashCode() ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (slug?.hashCode() ?: 0)
        return result
    }
}