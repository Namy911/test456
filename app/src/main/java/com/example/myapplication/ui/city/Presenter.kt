package com.example.myapplication.ui.city

import android.content.Context
import com.example.myapplication.data.model.CityItem
import org.json.JSONObject
import java.io.IOException

class Presenter(val context: Context) {
    private val TAG = "Presenter"
    fun setListAdapter(): MutableList<CityItem> {
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

    fun search(query: String?) =  if (query != null) {
        setListAdapter().filter { it.name == query }
    }else{
        emptyList()
    }

    private fun getJsonDataFromAsset(): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(FILE_NAME)
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    companion object {
        private const val FILE_NAME = "list_of_cities.json"
    }
}