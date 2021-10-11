package com.example.myapplication

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.ui.login.AuthReceiver
import com.example.myapplication.ui.city.SettingsFragment
import com.example.myapplication.ui.pef.AppPrefDataStore

class MainActivity : AppCompatActivity() {
    lateinit var authReceiver: AuthReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        lifecycleScope.launchWhenCreated {
//            AppPrefDataStore(this@MainActivity).setPrefUserLogin()
//        }
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.container, SettingsFragment.newInstance())
                .commit()
        }
        authReceiver = AuthReceiver()
        registerReceiver(authReceiver, IntentFilter().apply {
            addAction(BuildConfig.AUTH_LOGOUT)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(authReceiver)
    }

    fun navigateToSearch(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}