package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.container, SettingsFragment.newInstance())
                .commit()
        }
    }

    fun navigateToSearch(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, SearchFragment.newInstance())
            .commit()
    }
}