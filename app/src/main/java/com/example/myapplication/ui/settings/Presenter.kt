package com.example.myapplication.ui.settings

import com.example.myapplication.data.repository.Repository
import com.example.myapplication.ui.settings.contract.SearchView

class Presenter(
    private val view: SearchView?,
    private val repository: Repository,
) {
    fun search(query: String?) {
        if (query != null) {
            val result = repository.setListAdapter().filter { it.name == query }
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