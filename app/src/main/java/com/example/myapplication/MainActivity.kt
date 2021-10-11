package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.city.MainFragment
import com.example.myapplication.ui.city.SettingsFragment
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
                    navigateTo(SettingsFragment.newInstance())
                    true
                }
                R.id.home -> {
                    navigateTo(MainFragment.newInstance())
                    true
                }
                else -> { false}
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

    fun hideItemBottomMenu(@IdRes item: Int){
        val result = binding.bottomNavigation.menu.findItem(item)
        if (result.isVisible) {
            result.isVisible = false
        }
    }

    fun showItemBottomMenu(@IdRes item: Int){
        val result = binding.bottomNavigation.menu.findItem(item)
        if (!result.isVisible) {
            result.isVisible = true
        }
    }

    fun navigateTo(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}