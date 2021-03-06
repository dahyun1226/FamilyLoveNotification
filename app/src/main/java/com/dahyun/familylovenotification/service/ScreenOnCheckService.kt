package com.dahyun.familylovenotification.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import com.dahyun.familylovenotification.R
import com.dahyun.familylovenotification.br.SendSmsReceiver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScreenOnCheckService : Service() {

    private val sendSmsReceiver: SendSmsReceiver by lazy { SendSmsReceiver(this) }

    override fun onCreate() {
        super.onCreate()
        val filter = IntentFilter(Intent.ACTION_SCREEN_ON)
        registerReceiver(sendSmsReceiver, filter)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        intent?.let {
            val filter = IntentFilter(Intent.ACTION_SCREEN_ON)
            registerReceiver(sendSmsReceiver, filter)
        }
        val chan = NotificationChannel(
            ANDROID_CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_NONE
        )
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(chan)

        val builder = Notification.Builder(this, ANDROID_CHANNEL_ID)
            .setContentTitle(getString(R.string.app_name))
        val notification = builder.build()

        startForeground(NOTIFICATION_ID, notification)

        return START_REDELIVER_INTENT
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(sendSmsReceiver)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    companion object {
        const val ANDROID_CHANNEL_ID = "FamilyLoveNotification"
        const val NOTIFICATION_ID = 7777
        const val CHANNEL_NAME = "ScreenOnCheckService"
    }
}