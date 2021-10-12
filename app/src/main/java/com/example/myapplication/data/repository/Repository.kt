package com.example.myapplication.data.repository

import com.example.myapplication.data.model.CityItem
import com.example.myapplication.utlis.AssistedManager
import org.json.JSONObject

class Repository(private val assistedManager: AssistedManager) {

    fun setListAdapter(): MutableList<CityItem> {
        val jsonFileString = assistedManager.getJsonDataFromAsset() ?: ""
        val jsonArrayCity = JSONObject(jsonFileString).getJSONArray(NODE_CITY)
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

    fun clearData(){ assistedManager.clearData() }

    companion object{
        const val NODE_CITY = "cities"
    }
}