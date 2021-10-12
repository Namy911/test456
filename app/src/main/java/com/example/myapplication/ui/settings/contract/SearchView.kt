package com.example.myapplication.ui.settings.contract

import com.example.myapplication.data.model.CityItem

interface SearchView {
    fun setAdapter(list: List<CityItem>)
    fun pageNotFound()
}