package com.example.myapplication.ui.settings

import com.example.myapplication.data.repository.Repository
import com.example.myapplication.ui.settings.contract.SettingsView
import kotlinx.coroutines.launch

class SettingsPresenter(
    private val view: SettingsView,
    private val repository: Repository,
) {

    suspend fun clearData() {
         repository.clearData()
    }

    suspend fun  setPrefUnit(unit: Int){
        repository.setPrefUnit(unit)
    }
}