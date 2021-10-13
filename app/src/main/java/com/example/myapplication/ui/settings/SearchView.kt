package com.example.myapplication.ui.settings

import com.example.myapplication.data.model.CityItem

interface SearchView {
    fun setAdapter(list: List<CityItem>)
    fun pageNotFound()
}