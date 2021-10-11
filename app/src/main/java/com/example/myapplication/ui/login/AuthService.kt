package com.example.myapplication.ui.login

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder

class AuthService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        return null
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        clearData()
        applicationContext.sendBroadcast(AuthReceiver.newInstance())
        return START_STICKY
    }

    private fun clearData(){

    }

    companion object{
        fun newInstance(context: Context) = Intent(context, AuthService::class.java)
    }
}