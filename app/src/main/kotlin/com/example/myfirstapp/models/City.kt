package com.example.myfirstapp.models

data class City(
    val ID: Int,
    val Name: String,
    val CountryCode: String,
    val District: String,
    val Population: Int,
) {
    override fun equals(other: Any?): Boolean {
        return (other is City) && (this.ID == other.ID)
    }

    override fun hashCode(): Int {
        var result = ID
        result = 31 * result + Name.hashCode()
        result = 31 * result + CountryCode.hashCode()
        result = 31 * result + District.hashCode()
        result = 31 * result + Population
        return result
    }
}