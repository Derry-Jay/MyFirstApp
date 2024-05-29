package com.example.myfirstapp.models

import com.google.gson.annotations.SerializedName


data class ItemBase(

    @SerializedName("status") var status: Int? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("data") var data: List<Item>? = null,

    ) {}