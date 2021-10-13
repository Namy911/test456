package com.example.myapplication.ui.settings

import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.data.repository.Repository
import com.example.myapplication.pef.AppPrefDataStore
import com.example.myapplication.ui.search.SettingsView
import kotlinx.coroutines.flow.collect

class SettingsPresenter(
    private val view: SettingsView,
    private val repository: Repository,
) {

    suspend fun clearData() { repository.clearData() }

    suspend fun  setPrefUnit(unit: Int){ repository.setPrefUnit(unit) }
}