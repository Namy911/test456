package com.example.myapplication.ui.settings.search

import com.example.myapplication.data.repository.Repository
import com.example.myapplication.ui.settings.contract.SearchView
import kotlinx.coroutines.*

class SearchPresenter(
    private val searchView: SearchView,
    private val repository: Repository,
) {
    fun search(query: String?) {
        if (query != null) {
            // Get full list from json file
            val result = repository.getCities().filter { it.name == query }
            //check list
            if (result.isNotEmpty()) {
                searchView.setAdapter(result)
            } else {
                searchView.pageNotFound()
            }
        } else {
            searchView.pageNotFound()
        }
    }
}