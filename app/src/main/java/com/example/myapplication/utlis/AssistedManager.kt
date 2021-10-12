package com.example.myapplication.utlis

import android.content.Context
import com.example.myapplication.pef.AppPrefDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException

class AssistedManager(private val context: Context) {
    private val ioScope = CoroutineScope(Dispatchers.IO + Job())

    fun getJsonDataFromAsset(): String? {
        return try {
            context.assets.open(FILE_NAME)
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
    }

    fun clearData() {
        ioScope.launch {
            AppPrefDataStore(context).clearAllData()
        }
    }

    companion object {
        private const val FILE_NAME = "list_of_cities.json"
    }
}