package com.example.myfirstapp.models

import com.google.gson.annotations.SerializedName


data class User(
    @SerializedName("role") var role: String? = null,
    @SerializedName("_id") var Id: String? = null,
    @SerializedName("name") var name: String? = null,
) {
    override fun equals(other: Any?): Boolean {
        return other is User && this.Id == other.Id
    }

    override fun hashCode(): Int {
        var result = role?.hashCode() ?: 0
        result = 31 * result + (Id?.hashCode() ?: 0)
        result = 31 * result + (name?.hashCode() ?: 0)
        return result
    }
}