package com.example.myapplication.ui.login

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.myapplication.BuildConfig
import com.example.myapplication.MainActivity
import com.example.myapplication.ui.pef.AppPrefDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, p1: Intent) {
        clearData(context)
    }

    private fun clearData(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            AppPrefDataStore(context).clearAllData()
        }
        redirect(context)
    }

    private fun redirect(context: Context) {
        (context as MainActivity).navigateToSearch(LoginFragment.newInstance())
    }

    companion object {
        fun newInstance() = Intent(BuildConfig.AUTH_LOGOUT)
    }
}