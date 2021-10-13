package com.example.myapplication.utlis

import android.content.Context
import com.example.myapplication.R
import com.example.myapplication.pef.AppPrefDataStore
import com.google.gson.Gson
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import java.io.IOException
import java.io.InputStream

class AssetsManager(private val context: Context) {
    private val dataStore = AppPrefDataStore(context)

    fun getJsonDataFromAsset() = runCatching {
        context.assets.open(FILE_NAME)
            .bufferedReader()
            .use { it.readText() }
    }.getOrNull()

    suspend fun clearData() { dataStore.clearAllData() }

    suspend fun setPrefUnit(unit: Int) { dataStore.setUnitPref(unit) }

    companion object {
        private const val FILE_NAME = "list_of_cities.json"
    }
}