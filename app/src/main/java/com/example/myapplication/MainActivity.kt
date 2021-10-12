package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.settings.MainFragment
import com.example.myapplication.ui.settings.SettingsFragment
import com.example.myapplication.ui.login.LoginFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.container, LoginFragment.newInstance())
                .commit()
        }
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.settings -> {
                    navigateTo(SettingsFragment.newInstance(), SettingsFragment.TAG)
                    true
                }
                R.id.home -> {
                    navigateTo(MainFragment.newInstance(), MainFragment.TAG)
                    true
                }
                else -> { false }
            }
        }
    }

    fun hideBottomMenu(){
        if (binding.bottomNavigation.isVisible) {
            binding.bottomNavigation.visibility = View.GONE
        }
    }

    fun showBottomMenu(){
        if (!binding.bottomNavigation.isVisible) {
            binding.bottomNavigation.visibility = View.VISIBLE
        }
    }

    fun navigateTo(fragment: Fragment, tag: String?){
        supportFragmentManager.beginTransaction()
            .addToBackStack(tag)
            .replace(R.id.container, fragment)
            .commit()
    }

    fun navigateUp(){
        if (supportFragmentManager.backStackEntryCount > 0 ){
            supportFragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}