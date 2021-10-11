package com.example.myapplication.ui.city

import android.content.Context
import android.util.Log
import com.example.myapplication.data.model.CityItem
import com.example.myapplication.ui.pef.AppPrefDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.IOException

class Presenter(
    private val view: SearchView?,
    private val context: Context,
    private val ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO),
) {
    private val TAG = "Presenter"

    private fun setListAdapter(): MutableList<CityItem> {
        val jsonFileString = getJsonDataFromAsset()
        val jsonArrayCity = JSONObject(jsonFileString!!).getJSONArray("cities")
        val listAdapter = mutableListOf<CityItem>()
        for (index in 0 until jsonArrayCity.length()){
            val cityObject = jsonArrayCity.getJSONObject(index)
            val item = CityItem(
                name = cityObject.get("name").toString(),
                address = cityObject.get("address").toString()
            )
            listAdapter.add(item)
        }
        return listAdapter
    }

    fun search(query: String?) {
        if (query != null) {
            val result = setListAdapter().filter { it.name == query }
            result.forEach{
                Log.d(TAG, "search: ${it.name}")
            }
            if (result.isNotEmpty()) {
                view?.setAdapter(result.toMutableList())
            }else{
                view?.pageNotFound()
            }
        } else {
            view?.pageNotFound()
        }
    }

    private fun getJsonDataFromAsset(): String? {
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
        ioScope.launch { AppPrefDataStore(context).clearAllData() }
    }

    companion object {
        private const val FILE_NAME = "list_of_cities.json"
    }
}