package com.example.myapplication.ui.settings

import com.example.myapplication.data.repository.Repository
import com.example.myapplication.ui.settings.contract.SearchView

class Presenter(
    private val view: SearchView?,
    private val repository: Repository,
) {
    fun search(query: String?) {
        if (query != null) {
            // Get full list from json file
            val result = repository.readFile().filter { it.name == query }
            //check list
            if (result.isNotEmpty()) {
                view?.setAdapter(result)
            } else {
                view?.pageNotFound()
            }
        } else {
            view?.pageNotFound()
        }
    }

    fun clearData() { repository.clearData() }
}