package com.example.myapplication.pef

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class AppPrefDataStore(val context: Context) {
    private val KEY_USER_PASS = intPreferencesKey(USER_DATA_NODE)

    val userPass: Flow<Int> = context.dataStore.data
        .map { preferences -> preferences[KEY_USER_PASS] ?: 0 }

    suspend fun setPrefUserLogin(){
        context.dataStore.edit { preferences -> preferences[KEY_USER_PASS] = 12365 }
    }

    suspend fun clearAllData(){
        context.dataStore.edit{ it.clear() }
    }

    companion object{
        const val USER_DATA_NODE = "user_pass"
    }
}