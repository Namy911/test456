package com.example.myapplication.data.model

import com.google.gson.annotations.SerializedName

data class CityItem(
    @SerializedName("address") val address: String,
    @SerializedName("name") val name: String
)