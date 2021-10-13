package com.example.myapplication.ui.settings

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.os.bundleOf
import com.example.myapplication.pef.AppPrefDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AuthService : Service() {
    private val ioScope = CoroutineScope(Dispatchers.IO + Job())

    override fun onBind(intent: Intent): IBinder? { return null }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        ioScope.launch { AppPrefDataStore(applicationContext).setPrefUserLogin() }
        stopSelf()
        return START_STICKY
    }

    companion object {
        fun newInstance(context: Context) = Intent(context, AuthService::class.java)
    }
}