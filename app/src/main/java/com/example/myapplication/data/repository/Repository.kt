package com.example.myapplication.data.repository

import com.example.myapplication.data.model.City
import com.example.myapplication.data.model.CityItem
import com.example.myapplication.utlis.AssetsManager
import com.google.gson.*

class Repository(private val assetsManager: AssetsManager) {
    // Read from json file
    fun getCities(): List<CityItem> {
        val jsonFileString = assetsManager.getJsonDataFromAsset() ?: ""
        return Gson().fromJson(jsonFileString, City::class.java).cities
    }
    // clear data store
    suspend fun clearData(){ assetsManager.clearData() }

    suspend fun  setPrefUnit(unit: Int){ assetsManager.setPrefUnit(unit) }

    companion object{
        const val NODE_CITY = "cities"
    }
}