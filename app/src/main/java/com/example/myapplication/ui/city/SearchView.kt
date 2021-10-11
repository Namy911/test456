package com.example.myapplication.ui.city

import com.example.myapplication.data.model.CityItem

interface SearchView {
    fun setAdapter(list: MutableList<CityItem>)
    fun pageNotFound()
}