package com.example.myapplication

import androidx.fragment.app.Fragment

interface AppNavigation {
    fun navigateTo(fragment: Fragment, tag: String?)
    fun navigateUp()
    fun hideBottomMenu()
    fun showBottomMenu()
}